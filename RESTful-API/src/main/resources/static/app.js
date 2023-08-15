let loadAllBooksBtn = document.getElementById('loadBooks');
let tbody = document.getElementsByTagName('tbody')[0];

loadAllBooksBtn.addEventListener('click', loadAllBooks);

async function loadAllBooks(e) {
    let allBooks = await fetch("http://localhost:8080/api/books");
    allBooks = await allBooks.json();

    tbody.innerHTML = '';

    allBooks.forEach(book => {
        let row = document.createElement('tr');

        let title = document.createElement('td');
        title.textContent = book.title;

        let author = document.createElement('td');
        author.textContent = book.author.name;

        let isbn = document.createElement('td');
        isbn.textContent = book.isbn;

        let actions = document.createElement('td');

        let editBtn = document.createElement('button');
        editBtn.textContent = 'Edit';
        actions.appendChild(editBtn);

        let deleteBtn = document.createElement('button');
        deleteBtn.textContent = 'Delete';
        actions.appendChild(deleteBtn);

        row.appendChild(title);
        row.appendChild(author);
        row.appendChild(isbn);
        row.appendChild(actions);

        tbody.appendChild(row);
    });
}