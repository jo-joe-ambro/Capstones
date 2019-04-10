package com.techelevator;

import java.util.List;

public interface SiteDAO {
	public List<Site> getSite(String nameSearch);
	public List<Site> searchSiteByName(String nameSearch);

}
