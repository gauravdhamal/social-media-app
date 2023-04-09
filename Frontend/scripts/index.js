import navbar from "../components/navbar.js";

document.getElementById("navbar").innerHTML = navbar();

let commonUrl = "http://localhost:8888/";

async function fetchData() {
  let response = await fetch(commonUrl + `analytics/users`);
  if (response.status == 200) {
    let data = await response.json();
    console.log("data:", data);
    return data;
  }
}
fetchData();
