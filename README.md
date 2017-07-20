**# Vítejte na AstrumQChat wiki!**

Třídy:
 * LoginActivity
 * RegisterActivity
 * Chat 
 * AuthorDetail
 * Connection
 * UserProfile

**LoginActivity** -> Třída která slouží jako úvodní obrazovka, kde se vyskytuje Editovaci políčka a tlačítka. Tlačítko přihlásit se vyvolá akci pro přihlášení do chatovací aplikace, toto tlačítko vyvolá přihlášení ze třídy Connection, poté naplní pole uživatele(použivám ArrayList<UserProfile>). Po naplnění všech údajů spustí aktivitu a přejde na seznám zpráv.

![Login](http://i.imgur.com/kXuI6Fm.png)


**RegisterActivity** -> Ttřída slouží jako obrazovka pro přihlášení uživatelů, na této obrazovce se vyskytují editovací pole a tlačítko registrovat, toto tlačítko zaregistruje uživatele, naplní pole Uživatele(použivám ArrayList<UserProfile>). Po naplnění, spustí aktivitu a přejde na seznám zpráv.



![Registrace](http://i.imgur.com/6AOHomB.png)

**Chat** -> Třída se chová jako activity, v této třídě parsujeme data ze třídy connection, pro zprávy používáme metodu getMessage, která vrací hodnotu typu JSON, tyto zprávy poté s uživatelem ukládáme do ListView.

![Seznam Zpráv](http://i.imgur.com/N60dFk0.png)

**AuthorDetail** -> Obrazovka která slouží jako detail uživatele, na kterého klikneme v seznamu zpráv.
![Detail Uživatele](http://i.imgur.com/2hAG6CW.png)

**UserProfile** -> Třída uživatel.
**Connection **-> Má 3 metody Post(), Get(), PostMessage() všechny tyto metody vrací hodnotu JSON.

**Edit texty jak u Loginu, tak i u Registrace jsou validovány.**

**Aplikace vyšle požadavek pomocí třídy connection, server zašle odpověď, v odpovědi je pole typu JSON, toto pole se musí poté parsovat, parsujeme jej ve třídě LoginActivity, RegisterActivity a Chat, tento typ pole parsujeme abychom mohli uložit hodnoty z JSON do uživatele , nebo uložit hodnoty zprávy do seznamu konverzací spolu s hodnoty uživatele.**
