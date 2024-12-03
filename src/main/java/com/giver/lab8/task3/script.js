document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("calcForm");

    if (form) {
        form.addEventListener("submit", (e) => {
            e.preventDefault();

            // Получаем значения с поля ввода и заменяем запятую на точку, если она есть
            let num1 = document.getElementById("num1").value.replace(',', '.');
            let num2 = document.getElementById("num2").value.replace(',', '.');

            // Преобразуем строки в числа с плавающей точкой
            num1 = parseFloat(num1);
            num2 = parseFloat(num2);

            // Проверка, являются ли введённые данные числовыми
            if (isNaN(num1) || isNaN(num2)) {
                alert("Ошибка: введены некорректные данные. Пожалуйста, введите числовые значения.");
                return; // Остановить выполнение функции
            }

            const operation = document.querySelector('input[name="operation"]:checked').value;

            // Проверка деления на ноль
            if (operation === "divide" && num2 === 0) {
                alert("Ошибка: деление на ноль невозможно! А-А-А-А-А-А-А-А-А-А-А!!!!!.");
                return; // Остановить выполнение функции
            }

            let result;
            switch (operation) {
                case "add": result = num1 + num2; break;
                case "subtract": result = num1 - num2; break;
                case "multiply": result = num1 * num2; break;
                case "divide": result = num1 / num2; break;
            }

            // Переход на result.html с параметрами в URL
            window.location.href = `result.html?num1=${num1}&num2=${num2}&operation=${operation}&result=${result}`;
        });
    } else {
        // Отображение результатов на result.html
        const params = new URLSearchParams(window.location.search);
        const num1 = params.get("num1");
        const num2 = params.get("num2");
        const operation = params.get("operation");
        const result = params.get("result");

        const tbody = document.querySelector("#resultTable tbody");
        tbody.innerHTML = `
            <tr>
                <td>${num1}</td>
                <td>${num2}</td>
                <td>${operation}</td>
                <td>${result}</td>
            </tr>
        `;
    }
});
