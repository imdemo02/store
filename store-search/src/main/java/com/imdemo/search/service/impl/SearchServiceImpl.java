package com.imdemo.search.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imdemo.doc.ProductDoc;
import com.imdemo.param.ProductSearchParam;
import com.imdemo.pojo.Product;
import com.imdemo.search.service.SearchService;
import com.imdemo.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Time: 2022/11/30 21:37
 * @author: imdemo
 * description: 搜索模块的实现类
 */
@Service
@Slf4j
public class SearchServiceImpl implements SearchService {


    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 根据关键字和分页进行数据库数据查询
     * 1.判断关键字是否为null null  查询全部  不为null 根据 all 字段查询
     * 2.添加分页属性
     * 3.es查询
     * 4.结果处理
     *
     * @return
     */
    @Override
    public R search(ProductSearchParam productSearchParam) {

        SearchRequest searchRequest = new SearchRequest("product");
        String search = productSearchParam.getSearch();
        if (StringUtils.isEmpty(search)) {
            //null 不添加all关键字  查询全部
            searchRequest.source().query(QueryBuilders.matchAllQuery());
        } else {
            //不为null  添加 all关键字
            searchRequest.source().query(QueryBuilders.matchQuery("all", search));
        }
        //进行分页数据添加
        //偏移量  (当前页码-1)*页容量
        searchRequest.source().from((productSearchParam.getCurrentPage() - 1) * productSearchParam.getPageSize());
        searchRequest.source().size(productSearchParam.getPageSize());
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException("查询错误!");
        }
        SearchHits hits = searchResponse.getHits();

        //结果处理  R (msg,data,total)
        //获取查询到的结果数量
        long total = hits.getTotalHits().value;
        SearchHit[] hitsHits = hits.getHits();

        List<Product> productList = new ArrayList<>();
        //json处理器
        ObjectMapper objectMapper = new ObjectMapper();

        for (SearchHit hitsHit : hitsHits) {
            //查询到内容数据  productDoc模型对应的json数据
            String sourceAsString = hitsHit.getSourceAsString();
            Product product = null;
            try {
                //productDoc 中有一个 all属性  转成product对象时  没有all属性会报错
                // TODO:方法:借助注解 jackson提供忽略没有属性的注解
                product = objectMapper.readValue(sourceAsString, Product.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            productList.add(product);
        }
        R ok = R.ok(null, productList, total);
        log.info("SearchServiceImpl.search业务结束,结果:{}", ok);
        return ok;
    }

    /**
     * 同步调用 进行商品的插入 覆盖更新
     * 商品同步:插入和更新
     *
     * @param product
     * @return
     */
    @Override
    public R save(Product product) throws IOException {


        IndexRequest indexRequest = new IndexRequest("product").id(product.getProductId().toString());
        ProductDoc productDoc = new ProductDoc(product);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(productDoc);
        indexRequest.source(json, XContentType.JSON);
        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        return R.ok("数据同步成功!");
    }

    /**
     * 惊醒es库的商品删除
     *
     * @param productId
     * @return
     */
    @Override
    public R remove(Integer productId) throws IOException {

        DeleteRequest deleteRequest = new DeleteRequest("product").id(productId.toString());
        restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        return R.ok("es库的数据删除成功!");
    }
}
