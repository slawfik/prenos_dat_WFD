<#-- @ftlvariable name="" type="sk.fri.uniza.views.WeatherView" -->
<!-- calls getPersons().getName() and sanitizes it -->
<div class="section no-pad-bot" id="index-banner">
    <div class="container">
        <table id="example" class="striped" style="width:100%">
            <thead>
            <tr>
                <th>Miesto</th>
                <th>Dátum a čas merania</th>
                <th>Teplota</th>
                <th>Tlak</th>
                <th>Vlhkosť</th>
                <th>Oblačnost</th>
                <th>Rýchlosť vetra</th>
            </tr>
            </thead>
            <tbody>
            <#list getWobj() as wobj>
                <tr>
                    <td>
                        ${wobj.name}
                    </td>
                    <td>
                        ${wobj.date} ${wobj.time}
                    </td>
                    <td>
                        ${wobj.temp} °C
                    </td>
                    <td>
                        ${wobj.pressure} hPa
                    </td>
                    <td>
                        ${wobj.humidity}
                    </td>
                    <td>
                        ${wobj.all}
                    </td>
                    <td>
                        ${wobj.speed} km/h
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
    <br><br>
</div>

