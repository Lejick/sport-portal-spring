package org.portal.back.model.sherdog;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Fighter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nickname;
    @Column
    private String height;
    @Column
    private String weight;
    @Column
    private Date birthday;
    @Column
    private int wins;
    @Column
    private int winsKo;
    @Column
    private int winsSub;
    @Column
    private int winsDec;
    @Column
    private int winsOther;
    @Column
    private int losses;
    @Column
    private int lossesKo;
    @Column
    private int lossesSub;
    @Column
    private int lossesDec;
    @Column
    private int lossesOther;
    @Column
    private int draws;
    @Column
    private int nc;
    @Column
    private String picture;
    @Column
    private  String name;
    @Column
    private  String sherdogUrl;

    public Fighter() {
    }

    public Fighter(com.ftpix.sherdogparser.models.Fighter sdFighter) {
        setNickname(sdFighter.getNickname());
        setHeight(sdFighter.getHeight());
        setWeight(sdFighter.getWeight());
        setBirthday(sdFighter.getBirthday());
        setWins(sdFighter.getWins());
        setWinsKo(sdFighter.getWinsKo());
        setWinsSub(sdFighter.getWinsSub());
        setWinsDec(sdFighter.getWinsDec());
        setWinsOther(sdFighter.getWinsOther());
        setLosses(sdFighter.getLosses());
        setLossesKo(sdFighter.getLossesKo());
        setLossesSub(sdFighter.getLossesSub());
        setLossesDec(sdFighter.getLossesDec());
        setLossesOther(sdFighter.getLossesOther());
        setNc(sdFighter.getNc());
        setPicture(sdFighter.getPicture());
        setName(sdFighter.getName());
        setSherdogUrl(sdFighter.getSherdogUrl());
    }


    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeight() {
        return this.height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getWins() {
        return this.wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return this.losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getDraws() {
        return this.draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getNc() {
        return this.nc;
    }

    public void setNc(int nc) {
        this.nc = nc;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean equals(Object obj) {
        try {
            Fighter fighter = (Fighter) obj;
            return fighter.getSherdogUrl().equalsIgnoreCase(this.sherdogUrl);
        } catch (Exception var3) {
            return false;
        }
    }

    public String toString() {
        return "Fighter{name='" + this.name + '\'' + ", nickname='" + this.nickname + '\'' + ", height='" + this.height + '\'' + ", weight='" + this.weight + '\'' + ", birthday=" + this.birthday + ", wins=" + this.wins + ", losses=" + this.losses + ", draws=" + this.draws + ", nc=" + this.nc + ", picture='" + this.picture + '\'' + ", sherdogUrl='" + this.sherdogUrl + '\'' + '}';
    }

    public int getWinsKo() {
        return this.winsKo;
    }

    public void setWinsKo(int winsKo) {
        this.winsKo = winsKo;
    }

    public int getWinsSub() {
        return this.winsSub;
    }

    public void setWinsSub(int winsSub) {
        this.winsSub = winsSub;
    }

    public int getWinsDec() {
        return this.winsDec;
    }

    public void setWinsDec(int winsDec) {
        this.winsDec = winsDec;
    }

    public int getWinsOther() {
        return this.winsOther;
    }

    public void setWinsOther(int winsOther) {
        this.winsOther = winsOther;
    }

    public int getLossesKo() {
        return this.lossesKo;
    }

    public void setLossesKo(int lossesKo) {
        this.lossesKo = lossesKo;
    }

    public int getLossesSub() {
        return this.lossesSub;
    }

    public void setLossesSub(int lossesSub) {
        this.lossesSub = lossesSub;
    }

    public int getLossesDec() {
        return this.lossesDec;
    }

    public void setLossesDec(int lossesDec) {
        this.lossesDec = lossesDec;
    }

    public int getLossesOther() {
        return this.lossesOther;
    }

    public void setLossesOther(int lossesOther) {
        this.lossesOther = lossesOther;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSherdogUrl() {
        return this.sherdogUrl;
    }

    public void setSherdogUrl(String sherdogUrl) {
        this.sherdogUrl = sherdogUrl;
    }

}

