const hello = () => alert("It works");


async function logProducts() {
  const response = await fetch("/api/products");
  const products = await response.json();
  console.log(products);
}