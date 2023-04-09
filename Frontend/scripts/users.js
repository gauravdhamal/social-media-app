import navbar from "../components/navbar.js";

document.getElementById("navbar").innerHTML = navbar();

let commonUrl = "http://localhost:8888/";

let userPostForm = document.getElementById("userPostForm");

userPostForm.addEventListener("submit", (event) => {
  event.preventDefault();

  let formData = new FormData(event.target);

  let name = formData.get("name");
  let email = formData.get("email");
  let bio = formData.get("bio");

  let userObject = {
    name: "someName",
    email: "someEmail",
    bio: "someBio",
  };

  userObject.name = name;
  userObject.email = email;
  userObject.bio = bio;

  createUser(userObject);
});

// Create user POST request method.
let createUser = (userObject) => {
  const options = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(userObject),
  };

  fetch(commonUrl + `users/`, options)
    .then((response) => response.json())
    .then((savedUserObject) => {
      if (savedUserObject.description == "Validation error") {
        window.alert(savedUserObject.details);
      } else if (savedUserObject.description == "uri=/users/") {
        window.alert(`Username already exist try with another one.`);
      } else {
        window.alert(`User created with Id : ${savedUserObject.id}`);
        userPostForm.reset();
      }
    })
    .catch((error) => console.error("error : ", error));
};
