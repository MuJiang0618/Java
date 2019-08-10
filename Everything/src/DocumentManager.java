import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
 
import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class DocumentManager {
	public static RestHighLevelClient doc_client = new RestHighLevelClient(
            RestClient.builder(new HttpHost("localhost", 9200, "http")));
	
	public static String index_name = new IndexManager().index_name;
		
	public static void addDocument(Product product) throws IOException {
		IndexRequest indexRequest = new IndexRequest(index_name, "file", String.valueOf(product.getId()))
				.source("name", product.getName());
        doc_client.index(indexRequest);
        System.out.println("已经向ElasticSearch服务器增加文件："+ product.getName());
	}
	
	public static void delDocument(int id) throws IOException {
		DeleteRequest  deleteRequest = new DeleteRequest (index_name, "file", String.valueOf(id));
        doc_client.delete(deleteRequest);
        System.out.println("已经从ElasticSearch服务器上删除id="+id+"的文档");
	}

	public static void updateDocument(Product product) throws IOException {
		UpdateRequest  updateRequest = new UpdateRequest (index_name, "file", String.valueOf(product.Id))
                .doc("name", product.getName());         // 更新name字段
        doc_client.update(updateRequest);
        System.out.println("已经在ElasticSearch服务器更新文件 ");
	}

	public static void getDocument(int id) throws IOException {
		GetRequest request = new GetRequest(IndexManager.index_name, "file", String.valueOf(id));
        GetResponse response = doc_client.get(request);
        if(!response.isExists()){
            System.out.println("检查到服务器上 "+"id="+id+ "的文档不存在");
        } else {
            String source = response.getSourceAsString();
            System.out.print("获取到服务器上 "+"id="+id+ "的文档内容是：");
            System.out.println(source);
        }
	}
}