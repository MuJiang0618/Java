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
        System.out.println("�Ѿ���ElasticSearch�����������ļ���"+ product.getName());
	}
	
	public static void delDocument(int id) throws IOException {
		DeleteRequest  deleteRequest = new DeleteRequest (index_name, "file", String.valueOf(id));
        doc_client.delete(deleteRequest);
        System.out.println("�Ѿ���ElasticSearch��������ɾ��id="+id+"���ĵ�");
	}

	public static void updateDocument(Product product) throws IOException {
		UpdateRequest  updateRequest = new UpdateRequest (index_name, "file", String.valueOf(product.Id))
                .doc("name", product.getName());         // ����name�ֶ�
        doc_client.update(updateRequest);
        System.out.println("�Ѿ���ElasticSearch�����������ļ� ");
	}

	public static void getDocument(int id) throws IOException {
		GetRequest request = new GetRequest(IndexManager.index_name, "file", String.valueOf(id));
        GetResponse response = doc_client.get(request);
        if(!response.isExists()){
            System.out.println("��鵽�������� "+"id="+id+ "���ĵ�������");
        } else {
            String source = response.getSourceAsString();
            System.out.print("��ȡ���������� "+"id="+id+ "���ĵ������ǣ�");
            System.out.println(source);
        }
	}
}