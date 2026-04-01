import React, { useState } from "react";
import LocalUserList from "./LocalUserList";
import UserList from "./UserList";
import FakePostList from "./FakePostList";

function Dashboard() {

  const [activeComponent, setActiveComponent] = useState("local");

  return (
    <div style={{ textAlign: "center" }}>
      <h1>News Portal Dashboard</h1>

      <button onClick={() => setActiveComponent("local")}>
        Local Users
      </button>

      <button onClick={() => setActiveComponent("api")}>
        Users API
      </button>

      <button onClick={() => setActiveComponent("fake")}>
        Fake API Posts
      </button>

      <hr />

      {activeComponent === "local" && <LocalUserList />}
      {activeComponent === "api" && <UserList />}
      {activeComponent === "fake" && <FakePostList />}
    </div>
  );
}

export default Dashboard;