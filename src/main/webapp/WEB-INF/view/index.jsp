<%@page pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>
    
    <title><fmt:message key="dip.ui.title" /></title>

    <script src="${applicationScope.resourceServerUrl}js/page/index.js"></script>
</head>
<body>
	<%@include file="../include/menu-top.jsp"%>

    <div class="container min_H_400">

        <ul id="myTab" class="nav nav-tabs">
            <li class="active"><a href="#start" data-toggle="tab">Парамеры</a></li>
            <li><a href="#population" data-toggle="tab">Популяция</a></li>
            <li><a href="#matrixSm" data-toggle="tab">Матрица смежности</a></li>
            <li><a href="#grafModel" data-toggle="tab">Графовая модель</a></li>
            <li><a href="#visualization1" data-toggle="tab">Визуализация</a></li>
            <li><a href="#grafikCf" data-toggle="tab">График изменения ЦФ</a></li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div class="tab-pane active" id="start">

                <div class="container">

                    <div class="col-md-6">

                        <br/>

                            <label for="size" class="col-sm-4">Кол-во элементов:</label>
                            <input id="size" type="text" />
                            <br/>

                            <label for="sdnCount" class="col-sm-4">Кол-во соединений:</label>
                            <input id="sdnCount" type="text" />
                            <br/>

                            <label for="lenghHromosom" class="col-sm-4">Длина хромосомы:</label>
                            <input id="lenghHromosom" type="text" />
                            <br/>

                            <label for="numberOperationsGa" class="col-sm-4">Кол-во итераций ГА:</label>
                            <input id="numberOperationsGa" type="text" />
                            <br/>

                            <label for="numberOperationsEa" class="col-sm-4">Кол-во итераций ЭА:</label>
                            <input id="numberOperationsEa" type="text" />
                            <br/>
                            <br/>

                            <label for="veroyatnostKrossingovera" class="col-sm-4">Вероятность кроссинговера (%):</label>
                            <input id="veroyatnostKrossingovera" type="text" />
                            <br/>
                            <br/>

                            <label for="veroyatnostMutaci" class="col-sm-4">Вероятность мутации (%):</label>
                            <input id="veroyatnostMutaci" type="text" />
                            <br/>
                            <br/>

                            <label for="btnInput" class="col-sm-6"></label>
                            <button id="btnInput" class="btn btn-primary">Ok</button>

                            <span>&nbsp;&nbsp;&nbsp;</span>
                            <button id="refresh" class="btn btn-default">
                                <span class="glyphicon glyphicon-refresh"></span>
                            </button>
                        <br>
                        <br>
                        <div id="message1" class="alert alert-info" style="display: none">
                            <p>Пожалуйста, подождите...</p>
                        </div>

                        <div id="message" class="alert alert-success" style="display: none">
                            <p>DONE</p>
                        </div>
                    </div>
                </div>



            </div>


            <div class="tab-pane" id="population">
                <div class="container">
                    <br>
                    <div id = "divTable"></div>
                </div>
            </div>

            <div class="tab-pane" id="grafModel">
                <div class="container">
                <div style="height: 2000px; width: 2000px" id="mynetwork"></div>
                </div>
            </div>


            <div class="tab-pane" id="matrixSm">
                    <br>
                    <div id = "divTableSm"></div>
                    <br>
                    <br>
                    <br>
            </div>

            <div class="tab-pane" id="visualization1">
                <div>
                    <canvas id="cnv" width="2000" height="5000">Ставь нормальный браузер</canvas>
                </div>


            </div>

            <div class="tab-pane" id="grafikCf">
                <div id="visualization"></div>
                </div>
        </div>

    </div> <!-- /container -->

    <%@include file="../include/menu-bottom.jsp"%> 

</body>
</html>


