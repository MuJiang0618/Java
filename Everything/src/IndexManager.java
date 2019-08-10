import java.io.IOException;
 
import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class IndexManager {
	public static RestHighLevelClient index_client = new RestHighLevelClient(
            RestClient.builder(new HttpHost("localhost", 9200, "http")));  // ��es��������������
	
	public static String index_name = "filesystem";
	
	public static void main(String[] args) throws IOException {
	}
	
	public static boolean checkExistIndex(String indexName) throws IOException {
        boolean result =true;
        try {
            OpenIndexRequest openIndexRequest = new OpenIndexRequest(indexName);
            index_client.indices().open(openIndexRequest).isAcknowledged();
        } catch (ElasticsearchStatusException ex) {
            String m = "Elasticsearch exception [type=index_not_found_exception, reason=no such index]";
            if (m.equals(ex.getMessage())) {
                result = false;
            }
        }
        if(result)
            System.out.println("����:" +indexName + " ����");
        else
            System.out.println("����:" +indexName + " ������");
         
        return result;
    }
	
	public static void createIndex(String index_name) throws IOException{
		CreateIndexRequest request = new CreateIndexRequest(index_name);
        index_client.indices().create(request);
        System.out.println(""+index_name+" created");
	}
	
	public static void deleteIndex(String index_name) throws IOException{
		DeleteIndexRequest request = new DeleteIndexRequest(index_name);
		index_client.indices().delete(request);
		System.out.println(""+index_name+" deleted.");
	}

	public static void updateIndex(String index_name) throws IOException {
		
	}
}
