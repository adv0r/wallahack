package io.adva.wallhack.launch;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import io.adva.wallhack.models.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by admin on 18/04/15.
 */
public class MainLauncher {

    static {
        System.setProperty("logback.configurationFile", "config/logging/logback.xml");
    }

    final static Logger LOG = LoggerFactory.getLogger(MainLauncher.class);

    public static final String BASE = "http://es.wallapop.com/search?";
    public static final String LAT_BCN = "41.398077";
    public static final String LONG_BCN = "2.170432";
    public static final String CAT_ELECTRONIC = "12545";
    public static final String SORT_DATE_DES =  "creationDate-des";

    public static final String PRODUCT_BASE = "http://es.wallapop.com";

    public static void main (String[] args)
    {
        String keyword = "casita perro";
        int minPrice = 0;
        int maxPrice = 50;

        URL url = createUrl(keyword, minPrice, maxPrice,CAT_ELECTRONIC);

        Document doc = null;
        try {
            doc = Jsoup.connect(url.toString()).get();
        } catch (IOException e) {
            LOG.error(e.toString());
        }

        Elements containers = doc.getElementsByClass("container");
        Element container = containers.first();

        Elements cards = container.getElementsByClass("card");
        LOG.info("Scraping " + cards.size() + " products in Barcelona matching criteria : " +
                "category = Electronic; " +
                minPrice + "<price<" + maxPrice + "; ");
        LOG.info(url.toString());


        ArrayList<Product> productList = new ArrayList<Product>();
        for (int i=1; i< cards.size();i++) {
            Product p = parseProduct(cards.get(i));
            productList.add(p);
            LOG.trace(p.toString());
        }


        int maxItems = 10 ;
        LOG.info("Filtering latest "+maxItems+" items out of "+productList.size()+" retrieved:");

        printElems(productList, maxItems);
    }

    public static void printElems(ArrayList<Product> list,int max)
    {
        for (int i = 0; i <max; i++)
        {
        LOG.info((i+1)+" : "+ list.get(i).toString());
        }
    }
    public static Product parseProduct(Element card)
    {
        Product toRet = null;
        //Get price
        String priceString = getTextFromElement(card, "product-info-price").replace("€", "");
        double price = Double.parseDouble(priceString);

        String title = getTextFromElement(card, "product-info-title");

        String category = getTextFromElement(card, "product-info-category");

        Element el = card.getElementsByClass("product-info-title").first();
        String linkHref = PRODUCT_BASE+el.attr("href");

        String description = getTextFromPage(linkHref, "card-product-detail-description");
        String location = getTextFromPage(linkHref, "card-product-detail-location");

        Element el1 = card.getElementsByClass("card-product-image").first();
        String imgURL =el1.attr("src");

        Element sellerHref =(card.getElementsByAttribute("href").get(3));
        String sellerURL = PRODUCT_BASE+sellerHref.attr("href");

        toRet = new Product(title,description,linkHref,price,category,location,imgURL,sellerURL);

        return toRet;
    }




    private static String getTextFromPage(String url, String clazz)
    {
        String toRet = "";
        Document doc = null;
        try {
            doc = Jsoup.connect(url.toString()).get();
        } catch (IOException e) {
            LOG.error(e.toString());
        }

        Element target = doc.getElementsByClass(clazz).first();
        return target.text();
    }

    public static String getTextFromElement(Element el, String clazz)
    {
        String toReturn = "";
        Element target = el.getElementsByClass(clazz).first();
        return  target.text();
    }


    public static URL createUrl(String keyword, int minPrice, int maxPrice,String category){
        String minPriceStr ="";
        String maxPriceStr ="";
        keyword = keyword.replaceAll(" ","%20");
        if(minPrice!=0)
            minPriceStr=""+minPrice;
        if(maxPrice!=0)
            maxPriceStr=""+maxPrice;

        String urlString = BASE; //http://es.wallapop.com/search?
        urlString+="kws="+keyword+"&";
        urlString+="lat="+LAT_BCN+"&";
        urlString+="lng="+LONG_BCN+"&";
        if(category!="") {
            urlString += "catIds=" + category + "&";
        }
        urlString+="minPrice="+minPriceStr+"&";
        urlString+="maxPrice="+maxPriceStr+"&";
        urlString+="dist=0_&";
        urlString+="order="+SORT_DATE_DES;
        URL toRet = null;
        try {
            toRet = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return toRet;

    }

}
