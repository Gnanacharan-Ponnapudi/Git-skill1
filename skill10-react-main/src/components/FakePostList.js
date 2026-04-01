import React, { useEffect, useState } from "react";
import axios from "axios";

function FakePostList() {

  const [posts, setPosts] = useState([]);
  const [filteredPosts, setFilteredPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [selectedUser, setSelectedUser] = useState("all");

  const fetchPosts = () => {
    setLoading(true);
    axios.get("https://dummyjson.com/posts")
      .then((response) => {
        setPosts(response.data.posts);
        setFilteredPosts(response.data.posts);
        setLoading(false);
      })
      .catch((err) => {
        setError("Failed to fetch posts");
        setLoading(false);
      });
  };

  useEffect(() => {
    fetchPosts();
  }, []);

  const handleFilter = (e) => {
    const value = e.target.value;
    setSelectedUser(value);

    if (value === "all") {
      setFilteredPosts(posts);
    } else {
      const filtered = posts.filter(
        post => post.userId === parseInt(value)
      );
      setFilteredPosts(filtered);
    }
  };

  if (loading) return <p>Loading Posts...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div>
      <h2>Fake API Posts</h2>

      <button onClick={fetchPosts}>Refresh</button>

      <br /><br />

      <select value={selectedUser} onChange={handleFilter}>
        <option value="all">All Users</option>
        <option value="1">User 1</option>
        <option value="2">User 2</option>
        <option value="3">User 3</option>
      </select>

      {filteredPosts.map(post => (
        <div key={post.id}>
          <h4>{post.title}</h4>
          <p>{post.body}</p>
          <hr />
        </div>
      ))}
    </div>
  );
}

export default FakePostList;