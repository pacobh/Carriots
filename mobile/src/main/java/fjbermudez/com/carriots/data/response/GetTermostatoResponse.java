package fjbermudez.com.carriots.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetTermostatoResponse {

    @SerializedName("total_documents")
    @Expose
    int totalResults;


    @SerializedName("result")
    @Expose
    List<Result> results;

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
