import navbar from "../components/navbar.js";

document.getElementById("navbar").innerHTML = navbar();

let commonUrl = "http://localhost:8888/";

// Create user data
{
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
        }
        userPostForm.reset();
      })
      .catch((error) => console.error("error : ", error));
  };
}

// Update user data
{
  let userUpdateForm = document.getElementById("userUpdateForm");

  userUpdateForm.addEventListener("submit", (event) => {
    event.preventDefault();

    let formData = new FormData(event.target);

    let id = formData.get("id");
    let updatedName = formData.get("name");
    let updatedBio = formData.get("bio");

    let userObject = {
      id: 0,
      name: "someContent",
      bio: "someBio",
    };

    userObject.id = id;
    userObject.name = updatedName;
    userObject.bio = updatedBio;

    console.log("userObject:", userObject);

    updateUser(userObject);
  });

  let updateUser = (userObject) => {
    const options = {
      method: "PUT",
      headers: {
        "Content-Type": "application/Json",
      },
      body: JSON.stringify(userObject),
    };

    fetch(commonUrl + `users/${userObject.id}`, options)
      .then((response) => response.json())
      .then((updatedUserObject) => {
        if (updatedUserObject.description == "Validation error") {
          window.alert(updatedUserObject.details);
        } else if (
          updatedUserObject.description == `uri=/users/${userObject.id}`
        ) {
          window.alert(`User not found with Id : ${userObject.id}`);
        } else {
          window.alert(`User updated.`);
        }
      });
  };
}

{
  async function main() {
    let data = await getAllUsers();
    appendUsers(data);
  }

  main();

  // Get all users
  async function getAllUsers() {
    let response = await fetch(commonUrl + `analytics/users`);
    if (response.status == 200) {
      let data = await response.json();
      console.log("data:", data);
      return data;
    }
  }

  let appendUsers = (arrayOfUsers) => {
    let userTableBody = document.getElementById("userTableBody");
    userTableBody.innerHTML = "";
    arrayOfUsers.forEach((user) => {
      const row = document.createElement("tr");

      const idCell = document.createElement("td");
      idCell.textContent = user.id;
      row.appendChild(idCell);

      const nameCell = document.createElement("td");
      nameCell.textContent = user.name;
      row.appendChild(nameCell);

      const emailCell = document.createElement("td");
      emailCell.textContent = user.email;
      row.appendChild(emailCell);

      const bioCell = document.createElement("td");
      bioCell.textContent = user.bio;
      row.appendChild(bioCell);

      const createdAtCell = document.createElement("td");
      createdAtCell.textContent = user.created_at;
      row.appendChild(createdAtCell);

      const updatedAtCell = document.createElement("td");
      updatedAtCell.textContent = user.updated_at || "-";
      row.appendChild(updatedAtCell);

      userTableBody.appendChild(row);
    });
  };
}
