window.addEventListener('DOMContentLoaded', () => {
    let scrollPos = 0;
    const mainNav = document.getElementById('mainNav');
    const headerHeight = mainNav.clientHeight;
    window.addEventListener('scroll', function () {
        const currentTop = document.body.getBoundingClientRect().top * -1;
        if (currentTop < scrollPos) {
            // Scrolling Up
            if (currentTop > 0 && mainNav.classList.contains('is-fixed')) {
                mainNav.classList.add('is-visible');
            } else {
                console.log(123);
                mainNav.classList.remove('is-visible', 'is-fixed');
            }
        } else {
            // Scrolling Down
            mainNav.classList.remove(['is-visible']);
            if (currentTop > headerHeight && !mainNav.classList.contains('is-fixed')) {
                mainNav.classList.add('is-fixed');
            }
        }
        scrollPos = currentTop;
    });
})

function blankCheck() {
    let list = document.querySelectorAll("input, textarea");

    list.forEach((elem) => {
        let button = document.querySelector("[type = 'submit']")
        if (elem.value.trim() == '') button.setAttribute("disabled", "disabled");
        else button.removeAttribute("disabled");
    });
}

function addImage() {
    let input = document.createElement('input');
    input.setAttribute("type", "text");
    input.setAttribute("name", "images");
    input.setAttribute("placeholder", "link");
    input.setAttribute("required", "required");

    let li = document.createElement("li");
    li.setAttribute("class", "list-group-item");
    li.append(input);
    document.getElementById("images").append(li);
}

function removeImage() {
    document.getElementById("images").lastChild.remove();
}

function addParagraph() {
    let li = document.createElement("li");
    li.setAttribute("class", "list-group-item");

    let input = document.createElement("input");
    input.setAttribute("class", "post-content");
    input.setAttribute("name", "texts");
    input.setAttribute("placeholder", "Write your review");
    input.setAttribute("required", "required");

    li.append(input);
    document.getElementById("paragraphs").append(li);
}

function removeParagraph() {
    document.getElementById("paragraphs").lastChild.remove();
}

function addHashtag(){
    let li = document.createElement("li");
    li.setAttribute("class", "list-group-item");

    let input = document.createElement('input');
    input.setAttribute("type", "text");
    input.setAttribute("name", "tags");
    input.setAttribute("placeholder", "Hashtag");
    input.setAttribute("required", "required");
    li.append(input);
    document.getElementById("hashtags").append(li);
}

function removeHashtag(){
    document.getElementById("hashtags").lastChild.remove();
}

