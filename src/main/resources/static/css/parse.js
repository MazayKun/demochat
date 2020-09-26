(async function() {
    let response = await fetch("http://localhost:9090/messages");
    let json = await response.json();
    let elem = document.getElementsByClassName('message_history');
    let hist = "";
    for (let note of json) {
        let newelem = document.createElement('div');
        newelem.textContent = note.author + " : " + note.message;
        newelem.classList.add("message_bubble");
        elem[0].append(newelem);
    }
} ());