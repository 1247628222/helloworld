package com.lyl.helloworld.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "新闻内容实体", description = "新闻信息")
@TableName("t_content")
public class Content extends Model<Content> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "新闻序号", name = "id", example = "1")
    @TableId(value = "id", type = IdType.INPUT)
    private int id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题", name = "title", example = "今晚打老虎")
    @TableField("title")
    private String title;


    /**
     * 副标题
     */
    @ApiModelProperty(value = "副标题", name = "small_title", example = "一打一下午")
    @TableField("small_title")
    private String small_title;
    /**
     * /**
     * <p>
     * <p>
     * 浏览量
     */
    @ApiModelProperty(value = "浏览量", name = "page_view", example = "456")
    @TableField("page_view")
    private int page_view;

    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除", name = "Isdelete", example = "1")
    @TableField("Isdelete")
    private int Isdelete;

    /**
     * 发布人
     */
    @ApiModelProperty(value = "发布人", name = "issuer", example = "张三")
    @TableField("issuer")
    private String issuer;

    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间", name = "newstime", example = "2018-11-06 17:20:28")
    @TableField("newstime")
//    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date newstime;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", name = "state", example = "1")
    @TableField("state")
    private int state;
    /**
     * 记者
     */
    @ApiModelProperty(value = "记者", name = "reporter", example = "李四")
    @TableField("reporter")
    private String reporter;
    /**
     * 是否设置头条
     */
    @ApiModelProperty(value = "是否设置头条", name = "iftopline", example = "1")
    @TableField("iftopline")
    private int iftopline;
    /**
     * 栏目id
     */
    @ApiModelProperty(value = "栏目id", name = "column_id", example = "1")
    @TableField("column_id")
    private int column_id;

    /**
     * site_id
     */
    @ApiModelProperty(value = "site_id", name = "site_id", example = "1")
    @TableField("site_id")
    private int site_id;

    /**
     * 新闻内容
     */
    @ApiModelProperty(value = "新闻内容", name = "content", example = "内容111223344")
    @TableField("content")
    private String content;


    public Date getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(Date operate_time) {
        this.operate_time = operate_time;
    }

    /**
     * 最新操作时间
     */
    @ApiModelProperty(value = "最新操作时间", name = "operate_time", example = "2018-12-03 16:03:34")
    @TableField("operate_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date operate_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPage_view() {
        return page_view;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public void setPage_view(int page_view) {
        this.page_view = page_view;
    }

    public String getSmall_title() {
        return small_title;
    }

    public void setSmall_title(String small_title) {
        this.small_title = small_title;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }


    public Date getNewstime() {
        return newstime;
    }

    public void setNewstime(Date newstime) {
        this.newstime = newstime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public int getIftopline() {
        return iftopline;
    }

    public void setIftopline(int iftopline) {
        this.iftopline = iftopline;
    }

    public int getColumn_id() {
        return column_id;
    }

    public void setColumn_id(int column_id) {
        this.column_id = column_id;
    }

    public int getSite_id() {
        return site_id;
    }

    public void setSite_id(int site_id) {
        this.site_id = site_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIsdelete() {
        return Isdelete;
    }

    public void setIsdelete(int isdelete) {
        Isdelete = isdelete;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", page_view='" + page_view + '\'' +
                ", issuer='" + issuer + '\'' +
                ", newstime=" + newstime +
                ", state=" + state +
                ", iftopline=" + iftopline +
                ", column_id=" + column_id +
                ", site_id=" + site_id +
                ", content=" + content +
                ", operate_time=" + operate_time +
                '}';
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
