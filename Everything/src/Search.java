import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Search {
	public static RestHighLevelClient search_client = new RestHighLevelClient(
            RestClient.builder(new HttpHost("localhost", 9200, "http")));  // 与es服务器建立连接
	
	public static void main(String[] args) throws IOException {
		// 1. 建立索引
		IndexManager index_manager = new IndexManager();
		if(!IndexManager.checkExistIndex(IndexManager.index_name)) {
			IndexManager.createIndex(IndexManager.index_name);
		}
		
		// 2.添加文档
		ArrayList<Product> products = GetFiles.getFiles(GetFiles.start_dir);
		for(Product cur_p : products) {
			DocumentManager.addDocument(cur_p);       // 文档添加到es服务器索引中
		}
		
		// 3.查询
		String key_word = null;
		int start = 1;
		int count = 10;
		int num_item = 5;
		
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()) {
			key_word = scanner.nextLine();
			if(key_word.equals("quit"))
				break;
			SearchHits hits = search(key_word, start, count);
	        SearchHit[] searchHits = hits.getHits();
	        for (SearchHit hit : searchHits) {
	            System.out.println(hit.getSourceAsString());
	        }
	        System.out.println("输入 quit 结束查询");
		}
		scanner.close();
        search_client.close();
	}
	
	private static SearchHits search(String keyword, int start, int count) throws IOException {
        SearchRequest searchRequest = new SearchRequest(IndexManager.index_name);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //关键字匹配
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("name",keyword );
        //模糊匹配
        matchQueryBuilder.fuzziness(Fuzziness.AUTO);
        sourceBuilder.query(matchQueryBuilder);
        //第几页
        sourceBuilder.from(start);
        //第几条
        sourceBuilder.size(count);
 
        searchRequest.source(sourceBuilder);
        //匹配度从高到低
        sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
        SearchResponse searchResponse = search_client.search(searchRequest);
        SearchHits hits = searchResponse.getHits();
        return hits;
    }
}
