async function displayCompletedDossiers() {
    try {
        const response = await fetch('/api/dossiers/list');
        const patientFiles = await response.json();

        const tableBody = document.getElementById('dossiers-list-body');
        tableBody.innerHTML = '';

        patientFiles.forEach(d => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${d.patientName}</td>
                <td>${d.status}</td>
                <td>${d.createdDate}</td>
                <td>${d.completedDate}</td>
                <td>
                    <form action="/dossiers/delete" method="post">
                    <input type="hidden" name="id_dossier" value="${d.id}">
                    <button type="submit">Delete</button>
                    </form>
                </td>
            `;
            tableBody.appendChild(row);
        });
    } catch (error) {
        console.error('Error fetching dossiers:', error);
    }
}

window.onload = displayCompletedDossiers;
