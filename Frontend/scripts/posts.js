import navbar from "../components/navbar.js";

document.getElementById("navbar").innerHTML = navbar();

let commonUrl = "http://localhost:8888/";

// Create post data
{
  let postPostForm = document.getElementById("postPostForm");

  postPostForm.addEventListener("submit", (event) => {
    event.preventDefault();

    let formData = new FormData(event.target);

    let content = formData.get("content");

    let postObject = {
      content: "someContent",
    };

    postObject.content = content;

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
      .then((response) => {
        if (response.status == 201) {
          return response.json();
        } else {
          window.alert("Enter data");
        }
      })
      .then((savedPostObject) => {
        if (savedPostObject.description == "Validation error") {
          window.alert(savedPostObject.details);
        } else {
          window.alert(`Post created with Id : ${savedPostObject.id}`);
        }
        postPostForm.reset();
      })
      .catch((error) => console.error("error : ", error));
  };
}

// Update post data
{
  let postUpdateForm = document.getElementById("postUpdateForm");

  postUpdateForm.addEventListener("submit", (event) => {
    event.preventDefault();

    let formData = new FormData(event.target);

    let id = formData.get("id");
    let content = formData.get("content");

    let postObject = {
      id: 0,
      content: "someContent",
    };

    postObject.id = id;
    postObject.content = content;

    updatePost(postObject);
  });

  // Update post PUT request method.
  let updatePost = (postObject) => {
    const options = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(postObject),
    };

    fetch(commonUrl + `posts/${postObject.id}`, options)
      .then((response) => {
        return response.json();
      })
      .then((updatedPostObject) => {
        if (updatedPostObject.description == "Validation error") {
          window.alert(updatedPostObject.details);
        } else if (
          updatedPostObject.description == `uri=/posts/${postObject.id}`
        ) {
          window.alert(updatedPostObject.details);
        } else {
          window.alert(`Post updated.`);
        }
        postUpdateForm.reset();
      })
      .catch((error) => console.error("error : ", error));
  };
}
