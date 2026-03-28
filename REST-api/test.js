import http from "k6/http";

export let options = {
  vus: 1000,
  duration: "30s",
};

export default function () {
  http.get("http://localhost:9090/studnet/2");
}
