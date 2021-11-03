--
-- PostgreSQL database dump
--

-- Dumped from database version 13.3
-- Dumped by pg_dump version 13.3

-- Started on 2021-11-02 18:14:55 PDT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3274 (class 1262 OID 33290)
-- Name: INFO2413; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "INFO2413" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'C';


ALTER DATABASE "INFO2413" OWNER TO postgres;

\connect "INFO2413"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 201 (class 1259 OID 33382)
-- Name: FoodCategory; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."FoodCategory" (
    "CategoryID" integer NOT NULL,
    "CategoryName" character varying(20),
    "TotalSpent" real
);


ALTER TABLE public."FoodCategory" OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 33397)
-- Name: GroceryItem; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."GroceryItem" (
    "ItemID" integer NOT NULL,
    "InventoryID" integer,
    "CategoryID" integer,
    "FoodName" character varying(50),
    "Price" real,
    "ExpiryDate" date,
    "ShopDate" date,
    "QtyPurchased" integer,
    "QtyConsumed" integer,
    "Expired" boolean,
    "Picture" character varying(255)
);


ALTER TABLE public."GroceryItem" OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 33387)
-- Name: Inventory; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Inventory" (
    "InventoryID" integer NOT NULL,
    "UserID" integer,
    "ItemID" integer,
    "MonthlyTotal" real
);


ALTER TABLE public."Inventory" OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 33377)
-- Name: User; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."User" (
    "UserID" integer NOT NULL,
    "Username" character varying(20),
    "Password" character varying(20),
    "Email" character varying(50),
    "MonthlyBudget" real
);


ALTER TABLE public."User" OWNER TO postgres;

--
-- TOC entry 3266 (class 0 OID 33382)
-- Dependencies: 201
-- Data for Name: FoodCategory; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."FoodCategory" ("CategoryID", "CategoryName", "TotalSpent") FROM stdin;
\.


--
-- TOC entry 3268 (class 0 OID 33397)
-- Dependencies: 203
-- Data for Name: GroceryItem; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."GroceryItem" ("ItemID", "InventoryID", "CategoryID", "FoodName", "Price", "ExpiryDate", "ShopDate", "QtyPurchased", "QtyConsumed", "Expired", "Picture") FROM stdin;
\.


--
-- TOC entry 3267 (class 0 OID 33387)
-- Dependencies: 202
-- Data for Name: Inventory; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Inventory" ("InventoryID", "UserID", "ItemID", "MonthlyTotal") FROM stdin;
\.


--
-- TOC entry 3265 (class 0 OID 33377)
-- Dependencies: 200
-- Data for Name: User; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."User" ("UserID", "Username", "Password", "Email", "MonthlyBudget") FROM stdin;
\.


--
-- TOC entry 3128 (class 2606 OID 33386)
-- Name: FoodCategory FoodCategory_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."FoodCategory"
    ADD CONSTRAINT "FoodCategory_pkey" PRIMARY KEY ("CategoryID");


--
-- TOC entry 3132 (class 2606 OID 33401)
-- Name: GroceryItem GroceryItem_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."GroceryItem"
    ADD CONSTRAINT "GroceryItem_pkey" PRIMARY KEY ("ItemID");


--
-- TOC entry 3130 (class 2606 OID 33391)
-- Name: Inventory Inventory_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Inventory"
    ADD CONSTRAINT "Inventory_pkey" PRIMARY KEY ("InventoryID");


--
-- TOC entry 3126 (class 2606 OID 33381)
-- Name: User User_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User"
    ADD CONSTRAINT "User_pkey" PRIMARY KEY ("UserID");


--
-- TOC entry 3134 (class 2606 OID 33402)
-- Name: GroceryItem FK_GroceryItem.InventoryID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."GroceryItem"
    ADD CONSTRAINT "FK_GroceryItem.InventoryID" FOREIGN KEY ("InventoryID") REFERENCES public."Inventory"("InventoryID");


--
-- TOC entry 3133 (class 2606 OID 33392)
-- Name: Inventory FK_Inventory.UserID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Inventory"
    ADD CONSTRAINT "FK_Inventory.UserID" FOREIGN KEY ("UserID") REFERENCES public."User"("UserID");


-- Completed on 2021-11-02 18:14:55 PDT

--
-- PostgreSQL database dump complete
--

