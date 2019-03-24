package com.showCode.html;


import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.graphics.Color;

// Document
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
// to select the element in css
import org.jsoup.select.Elements;


public class CodeView extends WebView {
    private CodeViewTheme theme;
    private String encode;

    private Document document;

    // to show the picture in html
    private String baseUrl = null;
    private String preUrl = null;

    // constructor
    public CodeView(Context context, AttributeSet attributeSets, int styleIndex){
        super(context, attributeSets, styleIndex);
        getSettings().setJavaScriptEnabled(true);
        this.encode = "utf-8";
        this.theme = CodeViewTheme.DEFAULT;
    }

    public CodeView(Context context, AttributeSet attributeSets){
        this(context, attributeSets, 0);
    }

    public CodeView(Context context){
        this(context, null);
    }

    // following functions allow user to show a html with some code in
    // in an android application in different ways

    // if you only want to show a code snippet
    // and with this function, you can show a html file in a code style
    public void displayCode(Code code) {
        this.document = Jsoup.parse(BASE_HTML);
        document.head().after(getStyle());
        inputCode(code);
        loadDataWithBaseURL(baseUrl, document.html(), "text/html", encode, preUrl);
    }

    public void displayCode(String code) {
        Code __code = new Code(code);
        displayCode(__code);
    }

    // to show the html with code in , selected with css
    public void displayCodeCss(String htmlFile, String cssSelect) {
        initHtml(htmlFile);
        Elements elements = document.select(cssSelect);
        displayHtml(elements);
        loadDataWithBaseURL(baseUrl, document.html(), "text/html", encode, preUrl);
    }

    // to show the code with class
    public void diaplayCodeClass(String htmlFile, String codeClass){
        initHtml(htmlFile);
        Elements elements = document.getElementsByClass(codeClass);
        displayHtml(elements);
        loadDataWithBaseURL(baseUrl, document.html(), "text/html", encode, preUrl);
    }



    // following functions allow user to set attributes of the CodeView
    // set the encode of the file
    public CodeView setEncode(String encode){
        this.encode = encode;
        return this;
    }

    // set the base url
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    // set the pre url
    public void setPreUrl(String preUrl) {
        this.preUrl = preUrl;
    }

    // set the theme of the code
    public CodeView setTheme(CodeViewTheme theme){
        this.theme = theme;
        return this;
    }

    // set color
    public CodeView fillColor() {
        // web view
        setBackgroundColor(Color.parseColor(theme.getBackgroundColor()));
        return this;
    }


    // following functions and elements are private

    // add code
    private void inputCode(Code code) {
        document.body().appendElement("pre").appendElement("code").text(code.getCode());
    }

    // create a style and return
    private String getStyle() {
        return "<link rel=\"stylesheet\" href=\"file:///android_asset/highlight/styles/" + theme.getName() + ".css\"/>";
    }


    private static final String BASE_HTML =
            "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "\t<script src=\"file:///android_asset/highlight/highlight.pack.js\"></script>\n" +
                    "\t<script>hljs.initHighlightingOnLoad();</script>\n" +
                    "\t<title></title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "</body>\n" +
                    "</html>";

    // init the html document
    private void initHtml(String htmlFile) {
        this.document = Jsoup.parse(htmlFile);
        document.head().append("\n<script src=\"file:///android_asset/highlight/highlight.pack.js\"></script>\n");
        document.head().append("\n<script>hljs.initHighlightingOnLoad();</script>\n");
        document.head().append(getStyle());
    }

    // here is how we really display a html
    private void displayHtml(Elements htmlCode){
        if(htmlCode != null) {
            for (int i = 0; i < htmlCode.size(); i ++) {
                String line = htmlCode.get(i).text();
                htmlCode.get(i).html("<pre><code>" + line + "</code></pre>");
            }
        }
    }
}
