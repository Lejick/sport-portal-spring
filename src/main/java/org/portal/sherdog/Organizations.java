package org.portal.sherdog;

import org.portal.back.model.League;

import java.util.ArrayList;
import java.util.List;

public enum Organizations {
    UFC(1, "http://www.sherdog.com/organizations/Ultimate-Fighting-Championship-UFC-2"),
    BELLATOR(2, "http://www.sherdog.com/organizations/Bellator-MMA-1960"),
    WSOF(3, "http://www.sherdog.com/organizations/World-Series-of-Fighting-5449"),
    INVICTA_FC(4, "http://www.sherdog.com/organizations/Invicta-Fighting-Championships-4469"),
    ONE_FC(5, "http://www.sherdog.com/organizations/One-Championship-3877"),
    PRIDE(6, "http://www.sherdog.com/organizations/Pride-Fighting-Championships-3"),
    STRIKEFORCE(7, "http://www.sherdog.com/organizations/Strikeforce-716"),
    WEC(8, "http://www.sherdog.com/organizations/World-Extreme-Cagefighting-48"),
    DREAM(9, "http://www.sherdog.com/organizations/Dream-1357"),
    KSW(10, "http://www.sherdog.com/organizations/Konfrontacja-Sztuk-Walki-668"),
    FNG(11, "http://www.sherdog.com/organizations/Match-Nights-Global-2988"),
    M1(12, "http://www.sherdog.com/organizations/M1-Global-72"),
    ACB(13, "http://www.sherdog.com/organizations/Absolute-Championship-Berkut-8185"),
    CWFC(14, "http://www.sherdog.com/organizations/Cage-Warriors-Fighting-Championship-186"),
    PFL(15, "http://www.sherdog.com/organizations/Professional-Fighters-League-12241"),
    LFA(16, "http://www.sherdog.com/organizations/Legacy-Fighting-Alliance-LFA-11339"),
    CES(17, "http://www.sherdog.com/organizations/Classic-Entertainment-and-Sports-CES-MMA-3124"),
    SGK(18, " http://www.sherdog.com/organizations/Sengoku-1249");
    public String url;
    public Integer id;

    Organizations(Integer id, String url) {
        this.url = url;
        this.id = id;
    }
}
