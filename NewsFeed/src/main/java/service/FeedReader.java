package service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.enterprise.context.ApplicationScoped;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.fetcher.FeedFetcher;
import com.sun.syndication.fetcher.FetcherException;
import com.sun.syndication.fetcher.impl.HttpURLFeedFetcher;
import com.sun.syndication.io.FeedException;

@ApplicationScoped
public class FeedReader implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("-------News Feeder is Running-------");
		
			try {
				FeedFetcher fetcher=new HttpURLFeedFetcher();
				SyndFeed feed=fetcher.retrieveFeed(new URL("http://www.newindianexpress.com/World/rssfeed/"));
				
				for(Object o:feed.getEntries()){
					SyndFeed entry=(SyndFeed) o;
					System.out.println("------->Title"+entry.getTitle());
					System.out.println("------->Lijnk"+entry.getLink());
				}
			} catch (IllegalArgumentException | IOException | FeedException | FetcherException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
		
	}

}
