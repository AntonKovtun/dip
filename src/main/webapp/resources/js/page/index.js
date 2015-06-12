$(document).ready(function() {

    grafCf();

    $('#myTab a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });

    $("#btnInput").click(function(){
        $("#message1").css("display", "block");
        dip();

    });

    $("#refresh").click(function(){
        window.location.reload();
            $("#size").val("");
            $("#numberOperationsGa").val("");
            $("#numberOperationsEa").val("");
            $("#sdnCount").val("");
            $("#lenghHromosom").val("");
            $("#veroyatnostKrossingovera").val("");
            $("#veroyatnostMutaci").val("");


    });

});

function DipModel(size, numberOperationsGa, numberOperationsEa, sdnCount, lenghHromosom){
    this.size = size;
    this.numberOperationsGa = numberOperationsGa;
    this.numberOperationsEa = numberOperationsEa;
    this.sdnCount = sdnCount;
    this.lenghHromosom = lenghHromosom;
}

function dip(){

    var ctx = document.getElementById('cnv').getContext('2d');

    var dm = new DipModel(
        $("#size").val(),
        $("#numberOperationsGa").val(),
        $("#numberOperationsEa").val(),
        $("#sdnCount").val(),
        $("#lenghHromosom").val()

    );

    $.postJSON("diploma", dm, function(data) {
        if (!hasError(data)) {
            ctx.clearRect(0, 0, 5000, 5000);
            generateTable("divTable", data.value[0].rowCount, data.value[0].colCount, data.value[0].matrix);
            generateTableMs("divTableSm", data.value[0].size, data.value[0].size, data.value[0].matrixSm);
                var x = 50;
                var y = 50;
                var w = 50;
                var h = 50;
                var k = null;
                var arr = new Array();

            for (var i = 0; i < data.value[0].size; i++){

                arr[i] = new Array();
                    arr[i][0] = x;
                    arr[i][1]= y;
                    arr[i][2] = w;
                    arr[i][3] = h;

                var rec = ctx.strokeRect(x, y, w, h);
                var rec1 = ctx.strokeRect(x+10, y+10, w-20, h-20);
                ctx.fillText(data.value[0].population[i], x+25, y+25);
                k++;

                x = x + 100;

                if (k == 10) {
                    y = y + 100;
                    x = 50;
                    k = 0;
                }

                ctx.stroke();
            }




            draw(data.value[0].matrixSm);
            drawLine(data.value[0].matrixSm, arr, data.value[0].size, data.value[0].size, ctx);

            $("#message1").css("display", "none");
            $("#message").css("display", "block");
            setTimeout(function(){$("#message").css("display", "none");}, 1000);

        } else if (data.value == "wrong"){
            $("#errLog").attr("style", "display: block");
        }

    });

}

function generateTable(id, rowCount, colCount, matrix){
    var strtable = "<table border='1'>";
    for (var i = 0; i < rowCount; i++){
        strtable+="<tr>";
        for (var j = 0; j < colCount; j++){
            strtable+="<td width='30px' align='center'>" + matrix[i][j] + "</td>";
        }
        strtable+="</tr>";
    }
    strtable+="</table>";

    $("#"+id).html("");
    $("#"+id).append(strtable);
}

function generateTableMs(id, rowCount, colCount, matrixSm){
    for (var l = 0; l < rowCount; l++){
        matrixSm[l][l] = 0;
    }
    var strtable = "<table border='1'>";
    for (var i = 0; i < colCount; i++){
        strtable+="<tr>";
        for (var j = 0; j < rowCount; j++){
            strtable+="<td width='50px' align='center'>" + matrixSm[i][j] + "</td>";
        }
        strtable+="</tr>";
    }
    strtable+="</table>";

    $("#"+id).html("");
    $("#"+id).append(strtable);
}

function drawLine(matrixSm, lineCoord, rowCount, colCount, ctx){
    for (var l = 0; l < rowCount; l++){
        matrixSm[l][l] = 0;
    }

    for (var i = 0; i < colCount; i++){
        for (var j = i; j < colCount; j++){
            if (matrixSm[i][j] == 1) {
                ctx.moveTo(lineCoord[i][0]+50, lineCoord[i][1]+25);
                ctx.lineTo(lineCoord[j][0], lineCoord[j][1]+25);
                ctx.stroke();
            }
        }
    }
}


function draw(array){
    var nodes = [];
    for (var i = 0; i < $("#size").val(); i++){
        nodes.push({
            id: i,
            label: i + 1
        });
    }

    var edges = [];
    for (var i = 0; i < $("#size").val(); i++){
        for (var j = i; j < $("#size").val(); j++){
            if(array[i][j] == 1){
                edges.push({
                    from: i,
                    to: j
                })
            }
        }
    }


// create a network
    var container = document.getElementById('mynetwork');

// provide the data in the vis format
    var data = {
        nodes: nodes,
        edges: edges
    };
    var options = {
        //groups: {
        // useDefaultGroups: true,
        //myGroup: {color:{background:'red'}, borderWidth:3}
        //}
    };

// initialize your network!
    var network = new vis.Network(container, data, options);
}


function grafCf(){
    var container = document.getElementById('visualization');
    var items = [
        {x: '2014-06-11', y: 10},
        {x: '2014-06-12', y: 25},
        {x: '2014-06-13', y: 30},
        {x: '2014-06-14', y: 10},
        {x: '2014-06-15', y: 15},
        {x: '2014-06-16', y: 30}
    ];

    var dataset = new vis.DataSet(items);
    var options = {
        start: '2014-06-10',
        end: '2014-06-18'
    };
    var graph2d = new vis.Graph2d(container, dataset, options);
}