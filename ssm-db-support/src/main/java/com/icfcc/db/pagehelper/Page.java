package com.icfcc.db.pagehelper;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Mybatis - 分页对象
 *
 * @author liuzh/abel533/isea533
 * @version 3.6.0
 *          项目地址 : http://git.oschina.net/free/Mybatis_PageHelper
 */

public class Page<E> extends ArrayList<E> {

    private static final long serialVersionUID = 1L;

    public static final String PAGE_SIZE_KEY = "pagesize";

    public static final String PAGE_NUMBER_KEY = "pagenum";

    public static final String DEFAULT_PAGE_KEY = "page";

    public static final Integer DEFAULT_PAGE_SIZE = 30;

    /**
     * 不进行count查询
     */
    private static final int NO_SQL_COUNT = -1;
    /**
     * 进行count查询
     */
    private static final int SQL_COUNT = 0;

    /**
     * 分页合理化
     */
    private Boolean reasonable;
    /**
     * 当设置为true的时候，如果pagesize设置为0（或RowBounds的limit=0），就不执行分页，返回全部结果
     */
    private Boolean pageSizeZero;

    private PageInfo pageInfo;

    public Page() {
        super();
        pageInfo = new PageInfo();
    }

    public Page(int pageNum, int pageSize) {
        this(pageNum, pageSize, SQL_COUNT, null);
    }

    public Page(int pageNum, int pageSize, boolean count) {
        this(pageNum, pageSize, count ? Page.SQL_COUNT : Page.NO_SQL_COUNT, null);
    }

    private Page(int pageNum, int pageSize, int total, Boolean reasonable) {
        super(0);
        if (pageNum == 1 && pageSize == Integer.MAX_VALUE) {
            pageSizeZero = true;
            pageSize = 0;
        }
        pageInfo = new PageInfo(pageNum, pageSize, total);
//        this.pageNum = pageNum;
//        this.pageSize = pageSize;
//        this.total = total;
        calculateStartAndEndRow();
        setReasonable(reasonable);
    }

    /**
     * int[] rowBounds
     * 0 : offset
     * 1 : limit
     */
    public Page(int[] rowBounds, boolean count) {
        this(rowBounds, count ? Page.SQL_COUNT : Page.NO_SQL_COUNT);
    }

    /**
     * int[] rowBounds
     * 0 : offset
     * 1 : limit
     */
    public Page(int[] rowBounds, int total) {
        super(0);
        if (rowBounds[0] == 0 && rowBounds[1] == Integer.MAX_VALUE) {
            pageSizeZero = true;
            this.pageInfo.pageSize = 0;
        } else {
            this.pageInfo.pageSize = rowBounds[1];
        }
        this.pageInfo.startRow = rowBounds[0];
        //RowBounds方式默认不求count总数，如果想求count,可以修改这里为SQL_COUNT
        this.pageInfo.setTotal(total);
        this.getPageInfo().endRow = this.getPageInfo().startRow + rowBounds[1];
    }

    public List<E> getResult() {
        return this;
    }

    @XmlAttribute
    public int getPages() {
        return this.pageInfo.pages;
    }

    public void setPages(int pages) {
        this.pageInfo.pages = pages;
    }

    @XmlAttribute
    public int getEndRow() {
        return this.pageInfo.endRow;
    }

    @XmlAttribute
    public int getPageNum() {
        return this.pageInfo.pageNum;
    }

    public void setPageNum(int pageNum) {
        //分页合理化，针对不合理的页码自动处理
        this.pageInfo.pageNum = ((reasonable != null && reasonable) && pageNum <= 0) ? 1 : pageNum;
    }

    @XmlAttribute
    public int getPageSize() {
        return this.pageInfo.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageInfo.setPageSize(pageSize);
    }

    public int getStartRow() {
        return this.pageInfo.startRow;
    }

    @XmlAttribute
    public long getTotal() {
        return this.pageInfo.total;
    }

    public void setTotal(long total) {
        this.pageInfo.total = total;
        if (this.pageInfo.pageSize > 0) {
            this.pageInfo.pages = (int) (total / this.pageInfo.pageSize + ((total % this.pageInfo.pageSize == 0) ? 0 : 1));
        } else {
            this.pageInfo.pages = 0;
        }
        //分页合理化，针对不合理的页码自动处理
        if ((reasonable != null && reasonable) && this.pageInfo.pageNum > this.pageInfo.pages) {
            this.pageInfo.pageNum = this.pageInfo.pages;
            calculateStartAndEndRow();
        }
    }

    public void setReasonable(Boolean reasonable) {
        if (reasonable == null) {
            return;
        }
        this.reasonable = reasonable;
        //分页合理化，针对不合理的页码自动处理
        if (this.reasonable && this.pageInfo.pageNum <= 0) {
            this.pageInfo.pageNum = 1;
            calculateStartAndEndRow();
        }
    }

    public Boolean getReasonable() {
        return reasonable;
    }

    public Boolean getPageSizeZero() {
        return pageSizeZero;
    }

    public void setPageSizeZero(Boolean pageSizeZero) {
        if (pageSizeZero != null) {
            this.pageSizeZero = pageSizeZero;
        }
    }

    /**
     * 计算起止行号
     */
    private void calculateStartAndEndRow() {
        this.pageInfo.startRow = this.pageInfo.pageNum > 0 ? (this.pageInfo.pageNum - 1) * this.pageInfo.pageSize : 0;
        this.pageInfo.endRow = this.pageInfo.startRow + this.pageInfo.pageSize * (this.pageInfo.pageNum > 0 ? 1 : 0);
    }

    public boolean isCount() {
        return this.pageInfo.total > NO_SQL_COUNT;
    }

    //增加链式调用方法

    /**
     * 设置页码
     *
     * @param pageNum
     * @return
     */
    public Page<E> pageNum(int pageNum) {
        //分页合理化，针对不合理的页码自动处理
        this.pageInfo.pageNum = ((reasonable != null && reasonable) && pageNum <= 0) ? 1 : pageNum;
        return this;
    }

    /**
     * 设置页面大小
     *
     * @param pageSize
     * @return
     */
    public Page<E> pageSize(int pageSize) {
        this.pageInfo.pageSize = pageSize;
        calculateStartAndEndRow();
        return this;
    }

    /**
     * 是否执行count查询
     *
     * @param count
     * @return
     */
    public Page<E> count(Boolean count) {
        this.pageInfo.total = count ? Page.SQL_COUNT : Page.NO_SQL_COUNT;
        return this;
    }

    /**
     * 设置合理化
     *
     * @param reasonable
     * @return
     */
    public Page<E> reasonable(Boolean reasonable) {
        setReasonable(reasonable);
        return this;
    }

    /**
     * 当设置为true的时候，如果pagesize设置为0（或RowBounds的limit=0），就不执行分页，返回全部结果
     *
     * @param pageSizeZero
     * @return
     */
    public Page<E> pageSizeZero(Boolean pageSizeZero) {
        setPageSizeZero(pageSizeZero);
        return this;
    }

    @SuppressWarnings({"unchecked","rawtypes"})
    public Page<E> cloneOnlyPageInfo() {
        Page page = new Page();
        page.setTotal(this.getTotal());
        page.setPageSize(this.getPageSize());
        page.setPageNum(this.getPageNum());
        page.setPages(this.getPages());
        return page;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Page{");
        sb.append("pageNum=").append(this.getPageInfo().getPageNum());
        sb.append(", pageSize=").append(this.getPageInfo().getPageSize());
        sb.append(", startRow=").append(this.getPageInfo().getStartRow());
        sb.append(", endRow=").append(this.getPageInfo().getEndRow());
        sb.append(", total=").append(this.getPageInfo().getTotal());
        sb.append(", pages=").append(this.getPageInfo().getPages());
        sb.append(", reasonable=").append(reasonable);
        sb.append(", pageSizeZero=").append(pageSizeZero);
        sb.append('}');
        return sb.toString();
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    @XmlRootElement
    public static class PageInfo {
        public PageInfo() {

        }

        public PageInfo(int pageNum, int pageSize, long total) {
            this.pageNum = pageNum;
            this.pageSize = pageSize;
            this.total = total;
        }

        /**
         * 页码，从1开始
         */
        private int pageNum;
        /**
         * 页面大小
         */
        private int pageSize = DEFAULT_PAGE_SIZE;
        /**
         * 起始行
         */
        private int startRow;
        /**
         * 末行
         */
        private int endRow;
        /**
         * 总数
         */
        private long total;
        /**
         * 总页数
         */
        private int pages;

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public void setTotal(long total) {
            this.total = total;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        @XmlAttribute
        public int getPageNum() {
            return pageNum;
        }

        @XmlAttribute
        public int getPageSize() {
            return pageSize;
        }

        @XmlAttribute
        public int getStartRow() {
            return startRow;
        }

        @XmlAttribute
        public int getEndRow() {
            return endRow;
        }

        @XmlAttribute
        public long getTotal() {
            return total;
        }

        @XmlAttribute
        public int getPages() {
            return pages;
        }
    }

}
