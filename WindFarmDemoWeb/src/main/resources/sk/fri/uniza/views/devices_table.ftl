<#-- @ftlvariable name="" type="sk.fri.uniza.views.DevicesView" -->
<!-- calls getPersons().getName() and sanitizes it -->
<div class="section no-pad-bot" id="index-banner">
    <div class="container">
        <table id="example" class="striped" style="width:100%">
            <thead>
            <tr>
                <th>Meno</th>
                <th>Umiestnenie</th>
                <th>role</th>
                <th>baseUrl</th>
            </tr>
            </thead>
            <tbody>
            <#list getDList() as dev>
                <tr>
                    <td>
                        ${dev.name}
                    </td>
                    <td>
                        ${dev.location}
                    </td>
                    <td>
                        ${dev.role}
                    </td>
                    <td>
                        ${dev.baseUrl}
                    </td>
                    <td>
                        <a onclick="onDelete('/persons/delete?id=${dev.getId()}')"
                           class="btn waves-effect waves-light red " name="action">
                            <i class="material-icons">delete_forever</i>
                        </a>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
    <br><br>
</div>
<!-- Modal Trigger -->
<#--<a class="waves-effect waves-light btn modal-trigger" href="#modal1">Modal</a>-->

<!-- Modal Structure -->
<div id="modal1" class="modal">
    <div class="modal-content">
        <h4>Chcete vymazať užívateľa?</h4>
        <p></p>
    </div>
    <div class="modal-footer">
        <a href="#!" class="modal-close waves-effect waves-red btn-flat">Nie</a>
        <a id="modal_href" href="#!" class="modal-close waves-effect waves-green btn-flat">Áno</a>
    </div>
</div>

<script>
    function onDelete(url) {
        $("#modal_href").attr("href", url);
        // var elem = document.querySelector("#modal1")
        // var instance = M.Modal.getInstance(elem);
        // instance.open();
        //
        var elem = document.querySelector("#modal1");
        var instance = M.Modal.init(elem);

        instance.open();


    }
</script>