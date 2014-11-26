
public class DataTest {
	private int id;
	private String question;
	private String domain;
	private String intent;
	private String answer;
	private NameMapper nameMapper;
	public DataTest(){
		nameMapper = new NameMapper();
	}
	public NameMapper getNameMapper() {
		return nameMapper;
	}
	public void setNameMapper(NameMapper nameMapper) {
		this.nameMapper = nameMapper;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getDomain() {
		return domain;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getIntent() {
		return intent;
	}
	public void setIntent(String intent) {
		this.intent = intent;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
