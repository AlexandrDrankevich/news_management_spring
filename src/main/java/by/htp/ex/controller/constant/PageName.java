package by.htp.ex.controller.constant;

public final class PageName {
    private PageName() {
    }

    public static final String BASELAYOUT_PAGE = "baseLayout";
    public static final String INDEX_PAGE = "index.jsp";
    public static final String ERROR_PAGE = "error.jsp";
    public static final String BASE_PAGE = "controller?command=go_to_base_page";
    public static final String BASE_PAGE_WITH_REG_PARAMETER = "controller?command=go_to_base_page&reg=reg";
    public static final String NEWS_LIST_PAGE = "controller?command=go_to_news_list";
    public static final String ADD_NEWS_PAGE = "controller?command=go_to_add_news_page";
    public static final String EDIT_NEWS_PAGE = "controller?command=go_to_edit_news&id=";
    public static final String VIEW_NEWS = "controller?command=go_to_view_news&id=";
}
