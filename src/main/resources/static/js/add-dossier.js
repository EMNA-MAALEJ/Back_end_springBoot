function convertDateToString(date) {
            const d = new Date(date);
            let month = '' + (d.getMonth() + 1);
            let day = '' + d.getDate();
            const year = d.getFullYear();

            if (month.length < 2) month = '0' + month;
            if (day.length < 2) day = '0' + day;

            return [year, month, day].join('-');
        }

        function handleSubmit(event) {
            event.preventDefault();
            const form = document.getElementById('addDossierForm');
            const createdDate = document.getElementById('createdDate').value;
            const completedDate = document.getElementById('completedDate').value;

            /*
            const errorMessage = document.getElementById('error-message');

            errorMessage.textContent = '';
            if (createdDate > completedDate) {
                errorMessage.textContent = 'The created date cannot be later than the completed date.';
                return;
            }

             */

            let createdDateString = convertDateToString(createdDate);
            let completedDateString = convertDateToString(completedDate);

            if (createdDateString == "NaN-NaN-NaN") { return; }
            if (completedDateString == "NaN-NaN-NaN") { completedDateString = ''; }

            const hiddenCreatedDateInput = document.createElement('input');
            hiddenCreatedDateInput.type = 'hidden';
            hiddenCreatedDateInput.name = 'createdDate';
            hiddenCreatedDateInput.value = createdDateString;

            const hiddenCompletedDateInput = document.createElement('input');
            hiddenCompletedDateInput.type = 'hidden';
            hiddenCompletedDateInput.name = 'completedDate';
            hiddenCompletedDateInput.value = completedDateString;

            form.appendChild(hiddenCreatedDateInput);
            form.appendChild(hiddenCompletedDateInput);

            form.submit();
        }
