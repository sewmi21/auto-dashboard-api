package sewmi.springbootmongodb.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class TestSummary {
private String id;
private String Total;
private String Passed;
private String Failed;
private String Skipped;

public String getTotal() {
	return Total;
}
public void setTotal(String total) {
	Total = total;
}
public String getPassed() {
	return Passed;
}
public void setPassed(String passed) {
	Passed = passed;
}
public String getFailed() {
	return Failed;
}
public void setFailed(String failed) {
	Failed = failed;
}
public String getSkipped() {
	return Skipped;
}
public void setSkipped(String skipped) {
	Skipped = skipped;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
}
