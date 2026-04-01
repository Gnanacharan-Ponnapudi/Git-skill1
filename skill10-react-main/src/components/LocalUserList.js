import React, { useEffect, useState } from "react";

function LocalUserList() {

  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    fetch("/users.json")
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to fetch local users");
        }
        return response.json();
      })
      .then((data) => {
        setUsers(data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err.message);
        setLoading(false);
      });
  }, []);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div>
      <h2>Local Users</h2>
      {users.map(user => (
        <div key={user.id}>
          <p><b>Name:</b> {user.name}</p>
          <p><b>Email:</b> {user.email}</p>
          <p><b>Phone:</b> {user.phone}</p>
          <hr />
        </div>
      ))}
    </div>
  );
}

export default LocalUserList;