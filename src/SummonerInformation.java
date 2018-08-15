import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import org.json.*;
public class SummonerInformation {
	String summonerName;
	long summonerId;
	String leagueLink;
	//Constructor
	public SummonerInformation(String name) throws IOException {
		//this.summonerName = name;
		name = name.replaceAll(" ", "");
		URLContent urlcontent = new URLContent("https://na.api.pvp.net/api/lol/na/v1.4/summoner/by-name/"+ name +"?api_key=40b38c6a-f845-45f7-85d1-4d0f3940a318");
		String content = urlcontent.getContent();
		JSONObject json = new JSONObject(content);
		JSONObject sumInf = json.getJSONObject((name.toLowerCase()));
		this.summonerName = sumInf.getString("name");
		this.summonerId = sumInf.getLong("id");
		this.leagueLink = "https://na.api.pvp.net/api/lol/na/v2.5/league/by-summoner/" + this.summonerId + "/entry?api_key=40b38c6a-f845-45f7-85d1-4d0f3940a318";
		
	}
	
	//Get current summoner's league information
	public String getSoloTier() throws IOException {
		
		URLContent urlcontent = new URLContent(leagueLink);
		if(urlcontent.isContent()) {
		String content = urlcontent.getContent();
		JSONObject jsonObj = new JSONObject(content);
		JSONArray jsonAry = jsonObj.getJSONArray("" + this.summonerId);
		JSONObject solo = jsonAry.getJSONObject(0);
		return solo.getString("tier");
			}
		else {
			return "This summoner has no league information";
		}
		}
	
	public String getSoloDivision() throws IOException {
		URLContent urlcontent = new URLContent(leagueLink);
		if(urlcontent.isContent()) {
		String content = urlcontent.getContent();
		JSONObject jsonObj = new JSONObject(content);
		JSONArray jsonAry = jsonObj.getJSONArray("" + this.summonerId);
		JSONObject solo = jsonAry.getJSONObject(0);
		JSONArray entries = solo.getJSONArray("entries");
		JSONObject entriesObj = entries.getJSONObject(0);	
		return entriesObj.getString("division");
		}
		else {
			return "This summoner has no league information";
		}
	}	
	

	public long getId() {
		return this.summonerId;
	}
}