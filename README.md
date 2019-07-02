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
Na zariadení sa hneď po štarte inicializuje vlákno, ktoré automaticky po 40-tich sekundách vytvorý rest požiadavku na <a href="https://openweathermap.org/">OpenWeather web</a> a následne dané dáta pošle na WindFarmDemo (back-end).</p>
<h1><a id="WindFarmDemo_13"></a>WindFarmDemo</h1>
<p>Predstavuje back-end aplikácie v ktorom bolo zo zadania demonštračne predneven i naprogramovaná <code>Oauth2</code> authentifikácia</p>
<blockquote>
<p>The overriding design goal for Markdown’s<br>
formatting syntax is to make it as readable<br>
as possible. The idea is that a<br>
Markdown-formatted document should be<br>
publishable as-is, as plain text, without<br>
looking like it’s been marked up with tags<br>
or formatting instructions.</p>
</blockquote>
<p>This text you see here is <em>actually</em> written in Markdown! To get a feel for Markdown’s syntax, type some text into the left window and watch the results in the right.</p>
<h3><a id="Tech_26"></a>Tech</h3>
<p>Dillinger uses a number of open source projects to work properly:</p>
<ul>
<li><a href="http://angularjs.org">AngularJS</a> - HTML enhanced for web apps!</li>
<li><a href="http://ace.ajax.org">Ace Editor</a> - awesome web-based text editor</li>
<li><a href="https://github.com/markdown-it/markdown-it">markdown-it</a> - Markdown parser done right. Fast and easy to extend.</li>
<li><a href="http://twitter.github.com/bootstrap/">Twitter Bootstrap</a> - great UI boilerplate for modern web apps</li>
<li><a href="http://nodejs.org">node.js</a> - evented I/O for the backend</li>
<li><a href="http://expressjs.com">Express</a> - fast node.js network app framework <a href="http://twitter.com/tjholowaychuk">@tjholowaychuk</a></li>
<li><a href="http://gulpjs.com">Gulp</a> - the streaming build system</li>
<li><a href="http://breakdance.io">Breakdance</a> - HTML to Markdown converter</li>
<li><a href="http://jquery.com">jQuery</a> - duh</li>
</ul>
<p>And of course Dillinger itself is open source with a <a href="https://github.com/joemccann/dillinger">public repository</a><br>
on GitHub.</p>
<h3><a id="Installation_43"></a>Installation</h3>
<p>Dillinger requires <a href="https://nodejs.org/">Node.js</a> v4+ to run.</p>
<p>Install the dependencies and devDependencies and start the server.</p>
<pre><code class="language-sh">$ <span class="hljs-built_in">cd</span> dillinger
$ npm install <span class="hljs-operator">-d</span>
$ node app
</code></pre>
<p>For production environments…</p>
<pre><code class="language-sh">$ npm install --production
$ NODE_ENV=production node app
</code></pre>
<h3><a id="Plugins_62"></a>Plugins</h3>
<p>Dillinger is currently extended with the following plugins. Instructions on how to use them in your own application are linked below.</p>
<table class="table table-striped table-bordered">
<thead>
<tr>
<th>Plugin</th>
<th>README</th>
</tr>
</thead>
<tbody>
<tr>
<td>Dropbox</td>
<td><a href="https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md">plugins/dropbox/README.md</a></td>
</tr>
<tr>
<td>Github</td>
<td><a href="https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md">plugins/github/README.md</a></td>
</tr>
<tr>
<td>Google Drive</td>
<td><a href="https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md">plugins/googledrive/README.md</a></td>
</tr>
<tr>
<td>OneDrive</td>
<td><a href="https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md">plugins/onedrive/README.md</a></td>
</tr>
<tr>
<td>Medium</td>
<td><a href="https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md">plugins/medium/README.md</a></td>
</tr>
<tr>
<td>Google Analytics</td>
<td><a href="https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md">plugins/googleanalytics/README.md</a></td>
</tr>
</tbody>
</table>
<h3><a id="Development_76"></a>Development</h3>
<p>Want to contribute? Great!</p>
<p>Dillinger uses Gulp + Webpack for fast developing.<br>
Make a change in your file and instantanously see your updates!</p>
<p>Open your favorite Terminal and run these commands.</p>
<p>First Tab:</p>
<pre><code class="language-sh">$ node app
</code></pre>
<p>Second Tab:</p>
<pre><code class="language-sh">$ gulp watch
</code></pre>
<p>(optional) Third:</p>
<pre><code class="language-sh">$ karma <span class="hljs-built_in">test</span>
</code></pre>
<h4><a id="Building_for_source_99"></a>Building for source</h4>
<p>For production release:</p>
<pre><code class="language-sh">$ gulp build --prod
</code></pre>
<p>Generating pre-built zip archives for distribution:</p>
<pre><code class="language-sh">$ gulp build dist --prod
</code></pre>
<h3><a id="Docker_108"></a>Docker</h3>
<p>Dillinger is very easy to install and deploy in a Docker container.</p>
<p>By default, the Docker will expose port 8080, so change this within the Dockerfile if necessary. When ready, simply use the Dockerfile to build the image.</p>
<pre><code class="language-sh"><span class="hljs-built_in">cd</span> dillinger
docker build -t joemccann/dillinger:<span class="hljs-variable">${package.json.version}</span> .
</code></pre>
<p>This will create the dillinger image and pull in the necessary dependencies. Be sure to swap out <code>${package.json.version}</code> with the actual version of Dillinger.</p>
<p>Once done, run the Docker image and map the port to whatever you wish on your host. In this example, we simply map port 8000 of the host to port 8080 of the Docker (or whatever port was exposed in the Dockerfile):</p>
<pre><code class="language-sh">docker run <span class="hljs-operator">-d</span> -p <span class="hljs-number">8000</span>:<span class="hljs-number">8080</span> --restart=<span class="hljs-string">"always"</span> &lt;youruser&gt;/dillinger:<span class="hljs-variable">${package.json.version}</span>
</code></pre>
<p>Verify the deployment by navigating to your server address in your preferred browser.</p>
<pre><code class="language-sh"><span class="hljs-number">127.0</span>.<span class="hljs-number">0.1</span>:<span class="hljs-number">8000</span>
</code></pre>
<h4><a id="Kubernetes__Google_Cloud_131"></a>Kubernetes + Google Cloud</h4>
<p>See <a href="https://github.com/joemccann/dillinger/blob/master/KUBERNETES.md">KUBERNETES.md</a></p>
<h3><a id="Todos_136"></a>Todos</h3>
<ul>
<li>Write MORE Tests</li>
<li>Add Night Mode</li>
</ul>
<h2><a id="License_141"></a>License</h2>
<p>MIT</p>
<p><strong>Free Software, Hell Yeah!</strong></p>
