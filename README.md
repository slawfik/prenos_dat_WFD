<h1><a id="Semestrlna_prca_z_predmetu_Prenos_Dt_0"></a>Semestrálna práca z predmetu Prenos Dát</h1>
<p>Semestrálna práca sa skladá z troch častí:</p>
<ul>
<li>WindFarmDemoWeb</li>
<li>WindFarmDemo</li>
<li>WFD_zariadenie</li>
</ul>
<h1><a id="WFD_zariadenie_8"></a>WFD_zariadenie</h1>
<p>Táto časť práce emululuje fyzické zariadenie a získava dáta o počasí na servery <a href="https://openweathermap.org/">OpenWeather web</a> pomocou restového rozhrania. Následne aktuálne dáta pošle na back-end aplikácie čiže na (WindFarmDemo) aplikáciu taktiež pomocov restového rozhrania. Zariadenie obsakuje <code>Config.class</code> v ktorom je potrebné zadefinovať meno a heslo daného zariadenia ktorým sa následne bude autorizovať na back-end. Ďalej je potrebné v tejto triede zadefinovať aj mesto z ktorého chceme získavať informácie o počasí a appiID, ktoré si vyžaduje OpenWeather web na autorizáciu.<br>
Zariadenia sú authentifikované pomocou <code>Basic authentifikácie</code> čiže meno a heslo je priamo vkladané do hlavičky http protokolu zakódované v <code>Base64</code>.<br>
Na zariadení sa hneď po štarte inicializuje vlákno, ktoré automaticky po 40-tich sekundách vytvorý rest požiadavku na <a href="https://openweathermap.org/">OpenWeather web</a> a následne dané dáta pošle na WindFarmDemo (back-end).<br>
Zariadenie obsahuje triedu <code>Dev_Resource.class</code> s:</p>
<blockquote>
<p>GET     /devLogin (sk.uniza.WFD_dev.resources.Dev_Resource)<br>
POST     /openwater (sk.uniza.WFD_dev.resources.Dev_Resource)<br>
POST     /get (sk.uniza.WFD_dev.resources.Dev_Resource)</p>
</blockquote>
<p><code>/devLogin</code> resource authentifikuje zariadenie na back-end a vráti response.ok ak sa authentifikácia podarila v opačnom prípade vráti response.BAD_REQUEST.<br>
<code>/openwater</code> resource otestuje spojenie voči <a href="https://openweathermap.org/">OpenWeather web</a>  serveru.<br>
<code>/get</code> resource je volaný vláknom a stará sa o získanie nových dát z webu a odoslaním na back -end.</p>
<h3><a id="WindFarmDemo_21"></a>WindFarmDemo</h3>
<p>Predstavuje back-end aplikácie v ktorom bolo zo zadania demonštračne predneven i naprogramovaná <code>Oauth2</code> authentifikácia a najaká restová komunikácia s WindFarmDemoWeb časťou.<br>
V tejto časi sme implemontovali triedy pre zariadenia ako:</p>
<ul>
<li><code>Device.class</code></li>
<li><code>DeviceDao.class</code></li>
<li><code>BasicDev_Authorizer.class</code></li>
<li><code>BasicDev_Authentificator.class</code></li>
<li><code>Dev_Resource.class</code></li>
</ul>
<p><code>Device.class</code> predstavuje základnú triedu zariadenia s parametrami ako meno, heslo, umiestnenie zariadenia (Prešov, Bratislava-kuchyna, Bratislava-firma…) a rola pre autorizáciu. <code>DeviceDao.class</code> dedí z triedy AbstractDAO a je to trieda určená na pomunikáciu s databázou a umožnuje vykonávať CRUD operácie nad jednotlivými zariadeniami. <code>BasicDev_Authorizer.class</code> a <code>BasicDev_Authentificator.class</code> obsahujú metódy pre aturizáciu a authentifikáciu zariadenií tieto triedy vstupujú do <code>AuthFiltra</code> pri inicializácií <code>jersey</code>. <code>Dev_Resource.class</code> je trieda, pomocou ktorej <code>jeresy</code> roztriedi jednotlivé resource a vykoná príslušnú obsluhu volania.<br>
Typy volaní obsiahnutých v tejto triede sú:</p>
<blockquote>
<p>GET     /api/authDevice (sk.fri.uniza.resources.Dev_Resource)<br>
GET     /api/authDevice/addDev (sk.fri.uniza.resources.Dev_Resource)<br>
GET     /api/authDevice/getWeather (sk.fri.uniza.resources.Dev_Resource)<br>
GET     /api/authDevice/selectDev (sk.fri.uniza.resources.Dev_Resource)<br>
POST    /api/authDevice/weather (sk.fri.uniza.resources.Dev_Resource)<br>
GET     /api/authDevice/{id} (sk.fri.uniza.resources.Dev_Resource)</p>
</blockquote>
<p>Ďalej sme implementovali aj triedy pre počasie ako základná <code>OpenWeatherOBJ.class</code>, <code>LiteWeatherOBJ.class</code> a  <code>OpenWeatherOBJ.class</code> pre prácu s databázou.</p>
<h3><a id="WindFarmDemoWeb_42"></a>WindFarmDemoWeb</h3>
<p>Posledná časť sa skladá z webowého rozhrania ktorá komunikuje s back-endom v tejto časti je použitý šablonovací systém freemarker. Pre zobrazenie výsledkov počasia na webovom rozhraní sme museli vytvoriť nový <code>.ftl</code> súbor v ktorom sme si zadefinovali rozloženie stránky a namapovali sme si jednotlivé atribúty triedy do polí. Následne sme si vytvolili View objekty ktoré sa mapovali. Príslušný resource vytvorý request na back-end v ktorom pripojí autentifikačný token vďaka ktorému sa na back-end-e autorizuje a bude mu povolený prístup. Ak je autorzovaný môže si vypýtať dáta z databázy a zobrazí ich na web stránke.</p>
<pre><code class="language-sh">$ <span class="hljs-built_in">cd</span> dillinger
$ npm install <span class="hljs-operator">-d</span>
$ node app
</code></pre>
<p><strong>Tomáš Slavkovský</strong> a <strong>Tomáš Glodžák</strong></p>
