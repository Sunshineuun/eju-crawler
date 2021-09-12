package com.qiusm.eju.crawler.competitor.ajk;

import com.qiusm.eju.crawler.HttpTest;

public class ErFangPcTest extends HttpTest {

    private static String erFangDetailUrl = "https://hangzhou.anjuke.com/prop/view/A5928353415?auction=200&hpType=2&entry=102&position=6&kwtype=filter&now_time=1631158531&region_ids=1806&click_url=https%3A%2F%2Flego-click.anjuke.com%2Fjump%3Ftarget%3DpZwY0ZnlsztdraOWUvYKuaYzP1n3PWbzriYOrHNLsHwBnycVrH0LriYLP1IWn1EznjnduhNKPHnkn1TYnW9LP1TkP1DKTHcQPjmYrHn3njb3nHTYPjnKnWn3njTKnWn3njTKnikQnkDLrik3PEDQPWnQnHN3PHnQPj01THNKwbnVNDnVENGsOsJnOChsOCB4OlvUlmaFOmBgl2AClpAdTHDKnEDKsHDKTHDYn1NLrHNvPHczPj01njcQPjEKP9DKnE7hPvm3nyw-radBPWDLsHE1rHEVmyEYriYvryEQuWDYuAc3nWnKnHE1PH0OPHmdnW0dPj9krj91n9DQPjndP1bdPWNzPHbdPjcdnW9QTEDKTEDKsEDKTEDzPH-DnHPaPaYdrDnksH7KnHEVwW6AwBdKPWPDnWb3wNcdnDnKnH9ksWDvPz3Lnz3QrHEKnTDkTEDQsjD1TgVqTHDknjndTHDknjDvPj9KmHckuHTvnHEVmyndPiYYm1DvsyF-mhnVnHb3mHmkrH01uhNvTEDYTEDKnTDLrik3PikzPHTQnEDQTyOdUAkKrH7-mhmYryNOPW0zrjE1u9&spread=filtersearch&from=from_esf_List_screen&index=6";

    private static String erFangCity = "https://shanghai.anjuke.com/sale/";

    /**
     * bk esf limit_count <= 20; limit_offset <= 2000
     *
     * @param args
     */
    public static void main(String[] args) {
        String response = httpUtils.proxyGet(erFangCity);
        System.out.println(response);
    }
}
