$(document).ready(function () {

    updateMultiplications();
    $('#multiplication-form').submit(function (event) {
        event.preventDefault();
        let factorA = $('.factorA').text();
        let factorB = $('.factorB').text();
        let result = $('.result').val();
        let userAlias = $('.userAlias').val();
        console.log('factorA === ' + factorA);
        console.log('factorB === ' + factorB);
        console.log('userAlias === ' + userAlias);
        console.log('result === ' + result);
        let data = {
            user: {alias: userAlias},
            multiplication: {
                factorA: factorA,
                factorB: factorB
            },
            resultAttempt: result
        };

        $.ajax({
            url: 'http://localhost:8760/results',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json',
            dataType: 'json',
            success: function (result) {
                console.log("result ==> " + JSON.stringify(result));
                if (result.correct) {
                    $('.result-multiplication').empty().append('The result is Correct == ' + JSON.stringify(result))
                } else {
                    $('.result-multiplication').empty().append('sorry your response does not correct == ' + JSON.stringify(result))
                }
                if (result.user.id != null)
                    updateStatsUser(result.user.id)
            }
        });
        updateMultiplications();
        for (i = 1; i <= 5000; i++) {
        }
        updateStats(userAlias);
        updateLeaderBoard();
    });


    $('.refresh').click(function () {
        updateMultiplications();
    });

    $('.refreshLeaders').click(function () {
        updateLeaderBoard();
    });

});

function updateMultiplications() {
    $(".result").val("");
    $(".userAlias").val("");
    console.log("get Data from multiplications");
    $.ajax(
        {
            url: "http://localhost:8760/multiplications/random",
            type: 'GET',
            accept: 'application/json'
        }
    ).then(function (data) {
        var content = JSON.stringify(data);
        console.log("data === " + content);
        console.log("factorA === " + data.factorA);
        console.log("factorB === " + data.factorB);
        $('.factorA').empty().append(data.factorA);
        $('.factorB').empty().append(data.factorB);

    });
}

function updateStats(alias) {
    $.ajax(
        {
            url: "http://localhost:8760/results/stats?alias=" + alias,
            type: 'GET',
            accept: 'application/json',
            success: function (response) {
                $("#stats-body").empty();

                resultAttemtps = response.resultAttempts;

                console.log('response ===> ' + JSON.stringify(response));

                resultAttemtps.forEach(function (row) {
                    $("#stats-body").append(
                        '<tr> ' +
                        '<td> ' + row.multiplication.factorA + '</td>' +
                        '<td> ' + row.multiplication.factorB + '</td>' +
                        '<td> ' + row.resultAttempt + '</td>' +
                        '<td> ' + row.correct + '</td>' +
                        +' </tr>'
                    );
                });

            }
        });
}


function updateStatsUser(userId) {
    console.log("called statsUser with userId " + userId);
    $.ajax({
        url: "http://localhost:8760/stats?userId=" + userId,
        success: function (statsResponse) {
            console.log("statsResponse ===> ", JSON.stringify(statsResponse));
            $('#stats-div').show();
            $(".stats-body").empty();
            if (statsResponse != null)
                $('.stats-body').append('<tr> ' +
                    '<td> ' + statsResponse.userId + '</td>' +
                    '<td> ' + statsResponse.score + '</td>' +
                    '<td> ' + statsResponse.badges.join() + '</td>'
                    + '</tr>');
        },
        error: function (statsResponse) {
            console.log("Error statsResponse ===> ", JSON.stringify(statsResponse));
            $('#stats-div').show();
            $(".stats-body").empty();
            $('.stats-body').append('<tr> ' +
                '<td> ' + statsResponse.userId + '</td>' +
                '<td> ' + statsResponse.score + '</td>' +
                '<td> ' + statsResponse.badges.join() + '</td>'
                + '</tr>');
        }
    })
}


function updateLeaderBoard() {
    $.ajax({
        url: "http://localhost:8760/leaders"
    }).then(function (leadersBoardResponse) {
        console.log("leaderBoardResponse ===> " + JSON.stringify(leadersBoardResponse));
        $('#leaders-body').empty();
        let leaderBoardRows = leadersBoardResponse.leaderBoardRows;
        console.log("leaderBoardRows === " + JSON.stringify(leaderBoardRows));
         leaderBoardRows.forEach(function (row) {
                if (row != null) {
                    $('#leaders-body').append('<tr>' +
                        '<td> ' + row.userId + '</td>' +
                        '<td> ' + row.totalScore + '</td>' +
                        '</tr>');
                }
            });
    })
}
