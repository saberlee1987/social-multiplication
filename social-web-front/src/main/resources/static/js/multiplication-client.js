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
            multiplication : {
                factorA : factorA,
                factorB : factorB
            },
            resultAttempt :result
        };
        $.ajax({
            url: 'http://localhost:8760/results',
            type: 'POST',
            data: JSON.stringify(data),
            contentType : 'application/json',
            dataType : 'json',
            success :function (result) {
                if (result.correct){
                    $('.result-multiplication').empty().append('The result is Correct == '+JSON.stringify(result))
                }else{
                    $('.result-multiplication').empty().append('sorry your response does not correct == '+JSON.stringify(result))
                }
            }
        })
    });

    $('.refresh').click(function () {
        updateMultiplications();
    })
});

class data {
    factorA;
    factorB;
}

function updateMultiplications() {
    console.log("get Data from multiplications");
    $.ajax(
        {
            url: "http://localhost:8760/multiplications/random"
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