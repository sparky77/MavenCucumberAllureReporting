package POC.Spider;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Spider {

    // Fields
    private static final int LIMITSearhPageDepth = 400;
    private Set<String> pagesVisited = new HashSet<>();
    private List<String> pagesToVisit = new LinkedList<>();

    // should this be crawl scrapper - images, content, health status
    public void search(String url, String searchWord){
        while(this.pagesVisited.size() < LIMITSearhPageDepth){
            String currentUrl;
            SpiderLeg leg = new SpiderLeg();
            if(this.pagesToVisit.isEmpty()){
                currentUrl = url;
                this.pagesVisited.add(url);
            } else {
                currentUrl = this.nextUrl();
            }
            leg.crawl(currentUrl);
            boolean success = leg.searchForWord(searchWord);
            if (success) {
                System.out.println("Word Found! : " + searchWord);
                //String.format("**Success** Word % found at &s", searchWord,currentUrl);
                //break;
            }
            this.pagesToVisit.addAll(leg.getLinks());
        }
        System.out.println(String.format("** DONE Visited %s web page(s) ", this.pagesToVisit.size()));
    }

    private String nextUrl() {
        String nextUrl;
        do{
            nextUrl = this.pagesToVisit.remove(0);
        } while (
                this.pagesVisited.contains(nextUrl));
        this.pagesVisited.add(nextUrl);
        return nextUrl;
    }

}
