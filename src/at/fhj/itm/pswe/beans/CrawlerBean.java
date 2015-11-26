package at.fhj.itm.pswe.beans;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Named;

import at.fhj.itm.pswe.pagecrawler.CrawlerDaemon;

@Named("CrawlerBean")
@Singleton
public class CrawlerBean {

	public CrawlerBean() {
		// TODO Auto-generated constructor stub
	}
	
		
	@Schedule(hour="2")
    public void runCrawler() {
    	
    	Thread t= new Thread(new CrawlerDaemon());
    	t.start();
    }
	

}
