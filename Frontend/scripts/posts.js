import navbar from "../components/navbar.js";

document.getElementById("navbar").innerHTML = navbar();

let commonUrl = "http://localhost:8888/";

let postPostForm = document.getElementById("postPostForm");

postPostForm.addEventListener("submit", (event) => {
  event.preventDefault();

  let formData = new FormData(event.target);

  let content = formData.get("content");

  let postObject = {
    content: "someContent",
  };

  postObject.content = content;

  console.log("postObject:", postObject);

  createPost(postObject);
});

// Create post POST request method.
let createPost = (postObject) => {
  const options = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(postObject),
  };

  fetch(commonUrl + `posts/`, options)
    .then((response) => response.json())
    .then((savedPostObject) => {
      if (savedPostObject.description == "Validation error") {
        window.alert(savedPostObject.details);
      } else {
        window.alert(`Post created with Id : ${savedPostObject.id}`);
        console.log("savedPostObject:", savedPostObject);
      }
    })
    .catch((error) => console.error("error : ", error));
};
