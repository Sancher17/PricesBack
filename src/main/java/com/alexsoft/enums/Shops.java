package com.alexsoft.enums;

public enum Shops {

    ALL_STORE(17891, " "),
    AMD(1689, "https://www.amd.by/#/search/_"),
    BELM1_BY(200, "https://belm.by/search/?search="),
    BELM_BY(874, "https://belm.by/search/?search="),
    BEZNAL_SHOP_BY(16421, "http://beznal-shop.by/catalogs-search-in-category/main?search_word="),
    BIGI(1791, "https://bigi.by/search/"), // нужен POST запрос
    BITS_BY(7616, "https://www.proficomp.by/beznal"), // нужен POST запрос
    CHUP_SVKOMP(1031, "https://hellocomputer.by/catalog?keyword="),
    DREAMSHOP(10007, "http://www.evrotek.by/catalogs-search?search_word="), // пандери на Онлинере //http://www
    // .evrotek.by/
    ELEMENT5(3467, "https://5element.by/#/search/"), //    ELEMENT5(3),
    EMPTY(236, " "),
    EMPTY_1(238, " "),
    ENTER_PLUS(198, " "),
    EVROTEK(7965, "http://www.evrotek.by/catalogs-search?search_word="), // пандери на Онлинере //http://www.evrotek.by/
    HP_SHOP(9373, "https://hp-shop.by/rezultaty-poiska.html?search="), //hp.shop.by
    IMARKET_BY(581, "https://imarket.by/catalog/?q="),
    INOTE_BY(12451, "https://www.inote.by/index.php?route=product/search&search="),
    IPRODUCT_BY(15697, " "), // пост только маки, можно делать запрос из названия модели sitemap.xml доступен
    ISTORE_BY(12828, "https://i-store.by/search?q="),
    IT_GOLD(16334, " "),
    K1(2, " "),
    KUPITUT(7, " "),
    LAPTOP_BY(115, "https://www.laptop.by/search?term="),
    MEGABIT(17525, " "), // сайт не работает
    NEWTON(6498, "https://newton.by/catalog/?q="),
    NEW_STORE_BY(10840, "https://new-store.by/index.php?route=product/search&search="),
    NM_GROUP(1340, " "), // только на онлайнере, нет совего сайта
    NOTE_BY(13436, "https://www.note.by/search?term="),
    NOVATEK_BY(2378, " "),// только на онлайнере, нет совего сайта , по крайней мере пока недоступен
    ONLINER(1, " "),
    RULEZ_BY(2518, "https://rulez.by/search/?q="),
    SATELIT(1326, "http://satelit.by/catalogs-search?search_word="),
    SHOP_24(2033, "https://24shop.by/search/?q="),
    SHOP_BY(6, " "),
    SILA(12735, "https://sila.by/search/"),
    SOCKET_BY(1378, "https://www.socket.by/search/?q="),
    TEHNO_HIT(1220, " "),  // только на онлайнере, нет совего сайта
    TRADE_BOOK_BY(13766, "http://tradebook.by/search/?search="),
    TTN_BY(2118, "https://www.ttn.by/shop/product/search?SearchForm%5Btext%5D="),
    ULTRACOMP(747, "https://ultra.by/catalogsearch/result?q="),
    VEK21(965, "https://www.21vek.by/search/&sa=/?term="),
    VICO_TECHNO(5363, " "), //https://www.viko-t.by/
    VIPCOMP_BY(3886, "https://vipcomp.by/search/?search="),
    YANDEXMARKET(5, " "),
    FOTOMIX(196, " "),
    PHOTOSHOP  (163, " "),
    ФОТОМАГАЗИН  (1042, " "),
    FOTERA_BY  (5023, " "),
    INTERFOTO_BY  (12333, " "),
    ZERKALKA_SHOP_BY  (13064, " ");

    private long id;
    private String url;

    Shops(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static Shops getName(String name) {
        return Shops.valueOf(name);
    }

    public static Shops getById(Integer id) {
        for (Shops e : Shops.values()) {
            if (e.id == id) {
                return e;
            }
        }
        return null;
    }

    public static String getNameOfValue(int value) {
        for (Shops e : Shops.values()) {
            if (e.id == value) {
                return e.name();
            }
        }
        return null;// not found
    }
}
