**# Vítejte na AstrumQChat wiki!**

Třídy:
 * LoginActivity
 * RegisterActivity
 * Connection
 * Chat
 * AuthorDetail
 * UserProfile

**LoginActivity** -> Třída která slouží jako úvodní obrazovka, kde se vyskytuje Editovaci políčka a tlačítka. Tlačítko přihlásit se vyvolá akci pro přihlášení do chatovací aplikace, toto tlačítko vyvolá přihlášení ze třídy Connection, poté naplní pole uživatele(použivám ArrayList<UserProfile>). Po naplnění všech údajů spustí aktivitu a přejde na seznám zpráv.

![Login](http://i.imgur.com/kXuI6Fm.png)


**RegisterActivity** -> Ttřída slouží jako obrazovka pro přihlášení uživatelů, na této obrazovce se vyskytují editovací pole a tlačítko registrovat, toto tlačítko zaregistruje uživatele, naplní pole Uživatele(použivám ArrayList<UserProfile>). Po naplnění, spustí aktivitu a přejde na seznám zpráv.

![Registrace](http://i.imgur.com/6AOHomB.png)

**Connection **-> Má 3 metody Post(), Get(), PostMessage() všechny tyto metody vrací hodnotu JSON, která se potom parsuje v LoginActivity, RegisterActivity, Chat

**Chat** -> Seznam zpráv, má metody pro parsování hodnoty typu JSON.
![Seznam Zpráv](http://i.imgur.com/N60dFk0.png)

**AuthorDetail** -> Obrazovka která slouží jako detail uživatele, na kterého klikneme v seznamu zpráv.
![Detail Uživatele](http://i.imgur.com/2hAG6CW.png)

**UserProfile** -> Třída uživatel, poté se používá jako ArrayList<UserProfile>.
