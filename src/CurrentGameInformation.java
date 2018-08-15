import java.io.IOException;
import org.json.*;
public class CurrentGameInformation {
	String informationLink;
	String leagueInfLink;
	long summonerId;
	URLContent urlContent;
	String content;
	JSONObject jsonObj;
	JSONArray jsonAry;
	URLContent url;
	String leagueContent;
	JSONObject leagueObj;
	JSONArray leagueAry;
	public CurrentGameInformation(String name) throws IOException {
		SummonerInformation summoner = new SummonerInformation(name);
		this.summonerId = summoner.getId();
		this.informationLink = "https://na.api.pvp.net/observer-mode/rest/consumer/getSpectatorGameInfo/NA1/" + this.summonerId + "?api_key=40b38c6a-f845-45f7-85d1-4d0f3940a318";
		 urlContent = new URLContent(informationLink);
		 content = urlContent.getContent();
		 System.out.println(content);
		 jsonObj = new JSONObject(content);
		 jsonAry = jsonObj.getJSONArray("participants");
		 makeLeagueInfLink();
		 url = new URLContent(leagueInfLink);
		 leagueContent = url.getContent();
		 leagueObj = new JSONObject(leagueContent);
		 //System.out.println(leagueInfLink);
		 
	}
	
	public String getParticipantName(int index) throws IOException{
		JSONObject summonerObj = jsonAry.getJSONObject(index);
		return summonerObj.getString("summonerName");
	}
	
	public long getParticipantId(int index) throws IOException{
		JSONObject summonerObj = jsonAry.getJSONObject(index);
		return summonerObj.getLong("summonerId");
	}
	
	public String getTier(String id) throws IOException{
		if(!leagueObj.isNull(id)) {
		leagueAry = leagueObj.getJSONArray(id);
		JSONObject summonerObj = leagueAry.getJSONObject(0);
		return summonerObj.getString("tier");
		}
		else {
			return "0";
		}
	}
	
	public String getDivision(String id) throws IOException {
		if(!leagueObj.isNull(id)) {
		leagueAry = leagueObj.getJSONArray(id);
		JSONObject summonerObj = leagueAry.getJSONObject(0);
		JSONArray entriesAry = summonerObj.getJSONArray("entries");
		summonerObj = entriesAry.getJSONObject(0);
		return summonerObj.getString("division");
		}
		else {
			return "0";
		}
	}
	
	private void makeLeagueInfLink() throws IOException {
		String ids = "";
		for(int i = 0; i < 10; i++) {
			if(i > 0) {
			ids = ids + "," + getParticipantId(i);
			}
			else {
				ids = ids + getParticipantId(i);
			}
		}
		leagueInfLink = "https://na.api.pvp.net/api/lol/na/v2.5/league/by-summoner/" + ids + "/entry?api_key=40b38c6a-f845-45f7-85d1-4d0f3940a318";
		
	}
	

}
