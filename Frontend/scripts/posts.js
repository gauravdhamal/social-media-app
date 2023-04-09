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

// Get all posts
{
  async function main() {
    let data = await getAllPosts();
    appendPosts(data);
  }

  main();

  // Get all posts
  async function getAllPosts() {
    let response = await fetch(commonUrl + `analytics/posts`);
    let data = await response.json();
    if (response.status == 200) {
      return data;
    } else {
      console.log(response);
    }
  }

  let appendPosts = (arrayOfPosts) => {
    let postTableBody = document.getElementById("postTableBody");
    postTableBody.innerHTML = "";
    arrayOfPosts.forEach((post) => {
      const row = document.createElement("tr");

      const idCell = document.createElement("td");
      idCell.textContent = post.id;
      row.appendChild(idCell);

      const contentCell = document.createElement("td");
      contentCell.textContent = post.content;
      row.appendChild(contentCell);

      const likesCell = document.createElement("td");
      likesCell.textContent = post.likes;
      row.appendChild(likesCell);

      const viewCell = document.createElement("td");
      const viewUserButton = document.createElement("button");
      viewUserButton.textContent = "Get User";
      viewCell.append(viewUserButton);
      row.appendChild(viewCell);
      viewUserButton.addEventListener("click", () => {
        let postId = idCell.textContent;
        getUserByPostId(postId).then((user) => {
          if (user == undefined) {
            window.alert(`No any user assigned to post : ${postId}`);
          } else {
            console.log("user:", user);
          }
        });
      });

      const actionCell = document.createElement("td");
      const editButton = document.createElement("button");
      editButton.textContent = "Edit";
      const deleteButton = document.createElement("button");
      deleteButton.textContent = "Delete";
      actionCell.append(editButton, " / ", deleteButton);
      row.appendChild(actionCell);
      editButton.addEventListener("click", () => {
        let postId = idCell.textContent;
        getPostById(postId).then((post) => {
          //   console.log("post:", post);
          fillForm(post);
        });
      });

      deleteButton.addEventListener("click", () => {
        const confirmed = confirm("Are you sure you want to delete this item?");
        if (confirmed) {
          let postId = idCell.textContent;
          deletePost(postId).then((message) => {
            window.alert(message);
            main();
          });
        }
      });

      const likeUnlikeCell = document.createElement("td");
      const likeButton = document.createElement("button");
      likeButton.textContent = "Like";
      const unlikeButton = document.createElement("button");
      unlikeButton.textContent = "Unlike";
      likeUnlikeCell.append(likeButton, " / ", unlikeButton);
      row.appendChild(likeUnlikeCell);
      likeButton.addEventListener("click", () => {
        let postId = idCell.textContent;
        likePostById(postId).then((message) => {
          main();
        });
      });

      unlikeButton.addEventListener("click", () => {
        let postId = idCell.textContent;
        unlikePostById(postId).then((message) => {
          main();
        });
      });

      postTableBody.appendChild(row);
    });
  };

  async function getUserByPostId(postId) {
    let response = await fetch(commonUrl + `posts/user/${postId}`);
    if (response.status == 200) {
      let data = await response.json();
      return data;
    }
  }

  async function likePostById(postId) {
    let response = await fetch(commonUrl + `posts/${postId}/like`, {
      method: "POST",
    });
    if (response.status == 202) {
      let data = await response.text();
      window.alert(`Post liked.`);
      return data;
    }
  }

  async function unlikePostById(postId) {
    let response = await fetch(commonUrl + `posts/${postId}/unlike`, {
      method: "POST",
    });
    if (response.status == 202) {
      let data = await response.text();
      window.alert(`Post unliked.`);
      return data;
    } else {
      let data = await response.json();
      window.alert(data.details);
    }
  }

  async function deletePost(postId) {
    let response = await fetch(commonUrl + `posts/${postId}`, {
      method: "DELETE",
    });
    if (response.status == 202) {
      let data = await response.text();
      return data;
    } else {
      let data = await response.json();
      if (data.description == `uri=/posts/${postId}`) {
        window.alert(data.details);
      }
    }
  }

  async function getPostById(postId) {
    let response = await fetch(commonUrl + `posts/${postId}`);
    if (response.status == 200) {
      let data = await response.json();
      return data;
    }
  }

  let dynamicPostUpdateForm = document.getElementById("dynamicPostUpdateForm");
  let oldId = document.getElementById("oldid");

  function fillForm(post) {
    let formData = new FormData(dynamicPostUpdateForm);

    let id = formData.get("id");
    oldId.innerText = post.id;
    console.log("oldId:", oldId);
  }
}
