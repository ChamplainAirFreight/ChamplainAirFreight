USE [master]
GO
/****** Object:  Database [CAFDB]    Script Date: 12/16/2020 6:20:26 PM ******/
CREATE DATABASE [CAFDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'CAFDB', FILENAME = N'D:\rdsdbdata\DATA\CAFDB.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 10%)
 LOG ON 
( NAME = N'CAFDB_log', FILENAME = N'D:\rdsdbdata\DATA\CAFDB_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [CAFDB] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [CAFDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [CAFDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [CAFDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [CAFDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [CAFDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [CAFDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [CAFDB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [CAFDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [CAFDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [CAFDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [CAFDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [CAFDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [CAFDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [CAFDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [CAFDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [CAFDB] SET  DISABLE_BROKER 
GO
ALTER DATABASE [CAFDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [CAFDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [CAFDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [CAFDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [CAFDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [CAFDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [CAFDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [CAFDB] SET RECOVERY FULL 
GO
ALTER DATABASE [CAFDB] SET  MULTI_USER 
GO
ALTER DATABASE [CAFDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [CAFDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [CAFDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [CAFDB] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [CAFDB] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [CAFDB] SET QUERY_STORE = OFF
GO
USE [CAFDB]
GO
/****** Object:  User [TestUser]    Script Date: 12/16/2020 6:20:27 PM ******/
CREATE USER [TestUser] FOR LOGIN [TestUser] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [MRidgway]    Script Date: 12/16/2020 6:20:27 PM ******/
CREATE USER [MRidgway] FOR LOGIN [MRidgway] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [KMay]    Script Date: 12/16/2020 6:20:27 PM ******/
CREATE USER [KMay] FOR LOGIN [KMay] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [DPierre]    Script Date: 12/16/2020 6:20:27 PM ******/
CREATE USER [DPierre] FOR LOGIN [DPierre] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [CAFProgram]    Script Date: 12/16/2020 6:20:27 PM ******/
CREATE USER [CAFProgram] FOR LOGIN [CAFProgram] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [CAFadmin]    Script Date: 12/16/2020 6:20:27 PM ******/
CREATE USER [CAFadmin] FOR LOGIN [CAFadmin] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [ADockan]    Script Date: 12/16/2020 6:20:27 PM ******/
CREATE USER [ADockan] FOR LOGIN [ADockan] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [TestUser]
GO
ALTER ROLE [db_ddladmin] ADD MEMBER [TestUser]
GO
ALTER ROLE [db_datareader] ADD MEMBER [TestUser]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [TestUser]
GO
ALTER ROLE [db_owner] ADD MEMBER [MRidgway]
GO
ALTER ROLE [db_ddladmin] ADD MEMBER [MRidgway]
GO
ALTER ROLE [db_datareader] ADD MEMBER [MRidgway]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [MRidgway]
GO
ALTER ROLE [db_owner] ADD MEMBER [KMay]
GO
ALTER ROLE [db_ddladmin] ADD MEMBER [KMay]
GO
ALTER ROLE [db_datareader] ADD MEMBER [KMay]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [KMay]
GO
ALTER ROLE [db_owner] ADD MEMBER [DPierre]
GO
ALTER ROLE [db_ddladmin] ADD MEMBER [DPierre]
GO
ALTER ROLE [db_datareader] ADD MEMBER [DPierre]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [DPierre]
GO
ALTER ROLE [db_owner] ADD MEMBER [CAFProgram]
GO
ALTER ROLE [db_owner] ADD MEMBER [CAFadmin]
GO
ALTER ROLE [db_owner] ADD MEMBER [ADockan]
GO
/****** Object:  Table [dbo].[Aircraft]    Script Date: 12/16/2020 6:20:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Aircraft](
	[AircraftID] [int] IDENTITY(1,1) NOT NULL,
	[ACModelID] [int] NOT NULL,
	[AircraftStatusID] [int] NOT NULL,
 CONSTRAINT [PK_Aircraft] PRIMARY KEY CLUSTERED 
(
	[AircraftID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[AircraftModels]    Script Date: 12/16/2020 6:20:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AircraftModels](
	[ACModelID] [int] IDENTITY(1,1) NOT NULL,
	[ACMake] [varchar](20) NOT NULL,
	[ACModel] [varchar](20) NOT NULL,
	[ACRange] [varchar](20) NOT NULL,
	[ACRangeClassification] [varchar](20) NOT NULL,
	[ACPayload] [float] NOT NULL,
	[ACLoadVolume] [float] NOT NULL,
 CONSTRAINT [PK_AircraftModels] PRIMARY KEY CLUSTERED 
(
	[ACModelID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Airports]    Script Date: 12/16/2020 6:20:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Airports](
	[AirportID] [int] IDENTITY(1,1) NOT NULL,
	[AirportName] [varchar](20) NOT NULL,
	[AirportLocation] [varchar](20) NOT NULL,
	[AirportHub] [bit] NOT NULL,
	[AirportDistanceFromHub] [float] NOT NULL,
 CONSTRAINT [PK_Airports] PRIMARY KEY CLUSTERED 
(
	[AirportID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CargoManifests]    Script Date: 12/16/2020 6:20:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CargoManifests](
	[FlightID] [int] NOT NULL,
	[ShipmentID] [int] NOT NULL,
 CONSTRAINT [PK_CargoManifests] PRIMARY KEY CLUSTERED 
(
	[FlightID] ASC,
	[ShipmentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ClientAddresses]    Script Date: 12/16/2020 6:20:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ClientAddresses](
	[ClientAddressID] [int] IDENTITY(1,1) NOT NULL,
	[ClientID] [int] NOT NULL,
	[ClientAddressLine1] [varchar](120) NOT NULL,
	[ClientAddressLine2] [varchar](120) NULL,
	[ClientAddressCity] [varchar](50) NOT NULL,
	[ClientAddressState] [varchar](2) NOT NULL,
	[ClientAddressZip] [varchar](10) NOT NULL,
 CONSTRAINT [PK_ClientAddresses] PRIMARY KEY CLUSTERED 
(
	[ClientAddressID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Clients]    Script Date: 12/16/2020 6:20:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Clients](
	[ClientID] [int] IDENTITY(1,1) NOT NULL,
	[ClientName] [varchar](30) NOT NULL,
	[ClientTypeID] [int] NOT NULL,
	[ClientPhoneNumber] [varchar](20) NOT NULL,
 CONSTRAINT [PK_Clients] PRIMARY KEY CLUSTERED 
(
	[ClientID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Flights]    Script Date: 12/16/2020 6:20:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Flights](
	[FlightID] [int] IDENTITY(1,1) NOT NULL,
	[AircraftID] [int] NOT NULL,
	[PilotID] [int] NOT NULL,
	[StartAirport] [int] NOT NULL,
	[EndAirport] [int] NOT NULL,
	[FlightStartTime] [smalldatetime] NULL,
	[FlightEndTime] [smalldatetime] NULL,
 CONSTRAINT [PK_Flights] PRIMARY KEY CLUSTERED 
(
	[FlightID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LUAircraftStatus]    Script Date: 12/16/2020 6:20:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LUAircraftStatus](
	[AircraftStatusID] [int] IDENTITY(1,1) NOT NULL,
	[AircraftStatus] [varchar](20) NULL,
 CONSTRAINT [PK_LUAircraftStatus] PRIMARY KEY CLUSTERED 
(
	[AircraftStatusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LUClientType]    Script Date: 12/16/2020 6:20:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LUClientType](
	[ClientTypeID] [int] IDENTITY(1,1) NOT NULL,
	[ClientType] [varchar](30) NULL,
 CONSTRAINT [PK_LUClientType] PRIMARY KEY CLUSTERED 
(
	[ClientTypeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LUShipmentStatus]    Script Date: 12/16/2020 6:20:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LUShipmentStatus](
	[ShipmentStatusID] [int] IDENTITY(1,1) NOT NULL,
	[ShipmentStatus] [varchar](20) NULL,
 CONSTRAINT [PK_LUShipmentStatus] PRIMARY KEY CLUSTERED 
(
	[ShipmentStatusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Pilots]    Script Date: 12/16/2020 6:20:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pilots](
	[PilotID] [int] IDENTITY(1,1) NOT NULL,
	[FirstName] [varchar](20) NOT NULL,
	[LastName] [varchar](30) NOT NULL,
	[DateOfBirth] [date] NOT NULL,
	[EmployeeNumber] [varchar](20) NOT NULL,
	[DateOFHire] [date] NOT NULL,
	[DateLeftCAF] [date] NULL,
 CONSTRAINT [PK_Pilots] PRIMARY KEY CLUSTERED 
(
	[PilotID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Shipments]    Script Date: 12/16/2020 6:20:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Shipments](
	[ShipmentID] [int] IDENTITY(1,1) NOT NULL,
	[ClientID] [int] NOT NULL,
	[ShipmentVolume] [float] NOT NULL,
	[ShipmentWeight] [float] NOT NULL,
	[ShipmentStatusID] [int] NOT NULL,
	[ShipmentStartDate] [date] NULL,
	[ShipmentEndDate] [date] NULL,
	[ShipmentNotes] [varchar](1000) NULL,
 CONSTRAINT [PK_Shipments] PRIMARY KEY CLUSTERED 
(
	[ShipmentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 12/16/2020 6:20:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[UserID] [int] IDENTITY(1,1) NOT NULL,
	[UserName] [varchar](50) NOT NULL,
	[PasswordHash] [binary](64) NOT NULL,
 CONSTRAINT [PK_UserNames] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Aircraft] ON 

INSERT [dbo].[Aircraft] ([AircraftID], [ACModelID], [AircraftStatusID]) VALUES (1, 1, 1)
INSERT [dbo].[Aircraft] ([AircraftID], [ACModelID], [AircraftStatusID]) VALUES (3, 8, 1)
INSERT [dbo].[Aircraft] ([AircraftID], [ACModelID], [AircraftStatusID]) VALUES (4, 8, 1)
INSERT [dbo].[Aircraft] ([AircraftID], [ACModelID], [AircraftStatusID]) VALUES (5, 8, 1)
INSERT [dbo].[Aircraft] ([AircraftID], [ACModelID], [AircraftStatusID]) VALUES (6, 8, 1)
INSERT [dbo].[Aircraft] ([AircraftID], [ACModelID], [AircraftStatusID]) VALUES (7, 8, 1)
INSERT [dbo].[Aircraft] ([AircraftID], [ACModelID], [AircraftStatusID]) VALUES (8, 8, 1)
INSERT [dbo].[Aircraft] ([AircraftID], [ACModelID], [AircraftStatusID]) VALUES (9, 9, 1)
INSERT [dbo].[Aircraft] ([AircraftID], [ACModelID], [AircraftStatusID]) VALUES (10, 9, 1)
INSERT [dbo].[Aircraft] ([AircraftID], [ACModelID], [AircraftStatusID]) VALUES (11, 9, 1)
INSERT [dbo].[Aircraft] ([AircraftID], [ACModelID], [AircraftStatusID]) VALUES (12, 10, 1)
INSERT [dbo].[Aircraft] ([AircraftID], [ACModelID], [AircraftStatusID]) VALUES (13, 10, 1)
INSERT [dbo].[Aircraft] ([AircraftID], [ACModelID], [AircraftStatusID]) VALUES (14, 1, 1)
INSERT [dbo].[Aircraft] ([AircraftID], [ACModelID], [AircraftStatusID]) VALUES (15, 1, 1)
INSERT [dbo].[Aircraft] ([AircraftID], [ACModelID], [AircraftStatusID]) VALUES (16, 9, 1)
SET IDENTITY_INSERT [dbo].[Aircraft] OFF
GO
SET IDENTITY_INSERT [dbo].[AircraftModels] ON 

INSERT [dbo].[AircraftModels] ([ACModelID], [ACMake], [ACModel], [ACRange], [ACRangeClassification], [ACPayload], [ACLoadVolume]) VALUES (1, N'Aero Spacelines', N'Mini Guppy', N'1995', N'Large Wide Bodied', 32000, 18.91)
INSERT [dbo].[AircraftModels] ([ACModelID], [ACMake], [ACModel], [ACRange], [ACRangeClassification], [ACPayload], [ACLoadVolume]) VALUES (8, N'BAE', N'HS 748', N'1065 Miles', N'Medium haul', 13227, 1942)
INSERT [dbo].[AircraftModels] ([ACModelID], [ACMake], [ACModel], [ACRange], [ACRangeClassification], [ACPayload], [ACLoadVolume]) VALUES (9, N'BAE', N'ATP CARGO', N'459 Miles', N'Short haul', 18077, 2754)
INSERT [dbo].[AircraftModels] ([ACModelID], [ACMake], [ACModel], [ACRange], [ACRangeClassification], [ACPayload], [ACLoadVolume]) VALUES (10, N'BAE', N'146-200F', N'1322 Miles', N'Medium haul', 22046, 2754)
SET IDENTITY_INSERT [dbo].[AircraftModels] OFF
GO
SET IDENTITY_INSERT [dbo].[Airports] ON 

INSERT [dbo].[Airports] ([AirportID], [AirportName], [AirportLocation], [AirportHub], [AirportDistanceFromHub]) VALUES (1, N'Burlington IAP', N'Burlington VT', 1, 0)
INSERT [dbo].[Airports] ([AirportID], [AirportName], [AirportLocation], [AirportHub], [AirportDistanceFromHub]) VALUES (2, N'DFW IAP', N'Fort Worth TX', 0, 1750)
INSERT [dbo].[Airports] ([AirportID], [AirportName], [AirportLocation], [AirportHub], [AirportDistanceFromHub]) VALUES (3, N'Dulles IAP', N'Washington DC', 0, 500)
INSERT [dbo].[Airports] ([AirportID], [AirportName], [AirportLocation], [AirportHub], [AirportDistanceFromHub]) VALUES (4, N'Laguardia', N'New York City NY', 0, 300)
INSERT [dbo].[Airports] ([AirportID], [AirportName], [AirportLocation], [AirportHub], [AirportDistanceFromHub]) VALUES (5, N'Los Angeles IAP', N'Los Angeles CA', 0, 2900)
SET IDENTITY_INSERT [dbo].[Airports] OFF
GO
INSERT [dbo].[CargoManifests] ([FlightID], [ShipmentID]) VALUES (2, 2)
GO
SET IDENTITY_INSERT [dbo].[ClientAddresses] ON 

INSERT [dbo].[ClientAddresses] ([ClientAddressID], [ClientID], [ClientAddressLine1], [ClientAddressLine2], [ClientAddressCity], [ClientAddressState], [ClientAddressZip]) VALUES (1, 7, N'10 Rich Way', N'', N'New York', N'NY', N'10023')
INSERT [dbo].[ClientAddresses] ([ClientAddressID], [ClientID], [ClientAddressLine1], [ClientAddressLine2], [ClientAddressCity], [ClientAddressState], [ClientAddressZip]) VALUES (2, 8, N'10 Rich Way', N'', N'New York', N'NY', N'10023')
INSERT [dbo].[ClientAddresses] ([ClientAddressID], [ClientID], [ClientAddressLine1], [ClientAddressLine2], [ClientAddressCity], [ClientAddressState], [ClientAddressZip]) VALUES (3, 9, N'2000 Allegheny Drive', N'', N'Pittsburgh', N'PA', N'15243')
INSERT [dbo].[ClientAddresses] ([ClientAddressID], [ClientID], [ClientAddressLine1], [ClientAddressLine2], [ClientAddressCity], [ClientAddressState], [ClientAddressZip]) VALUES (4, 10, N'231 Dynasty Road', N'', N'Manchester', N'VT', N'52854')
INSERT [dbo].[ClientAddresses] ([ClientAddressID], [ClientID], [ClientAddressLine1], [ClientAddressLine2], [ClientAddressCity], [ClientAddressState], [ClientAddressZip]) VALUES (5, 11, N'2950 Oil Circle', N'', N'Dallas', N'TX', N'75001')
INSERT [dbo].[ClientAddresses] ([ClientAddressID], [ClientID], [ClientAddressLine1], [ClientAddressLine2], [ClientAddressCity], [ClientAddressState], [ClientAddressZip]) VALUES (6, 18, N'20 Dollar Street', N'', N'Los Angeles', N'CA', N'90022')
INSERT [dbo].[ClientAddresses] ([ClientAddressID], [ClientID], [ClientAddressLine1], [ClientAddressLine2], [ClientAddressCity], [ClientAddressState], [ClientAddressZip]) VALUES (7, 19, N'123 rd sw eagle', N'Appartment 999', N'Maple Hills', N'WA', N'98038')
INSERT [dbo].[ClientAddresses] ([ClientAddressID], [ClientID], [ClientAddressLine1], [ClientAddressLine2], [ClientAddressCity], [ClientAddressState], [ClientAddressZip]) VALUES (8, 20, N'1323 rd sw eagle', N'Appartment 999', N'Valley Hills', N'CA', N'98038')
INSERT [dbo].[ClientAddresses] ([ClientAddressID], [ClientID], [ClientAddressLine1], [ClientAddressLine2], [ClientAddressCity], [ClientAddressState], [ClientAddressZip]) VALUES (9, 27, N'Pineapple', N'House', N'BikiniBottom', N'CA', N'90210')
INSERT [dbo].[ClientAddresses] ([ClientAddressID], [ClientID], [ClientAddressLine1], [ClientAddressLine2], [ClientAddressCity], [ClientAddressState], [ClientAddressZip]) VALUES (10, 33, N'test', N'test', N'test', N'AL', N'123457')
INSERT [dbo].[ClientAddresses] ([ClientAddressID], [ClientID], [ClientAddressLine1], [ClientAddressLine2], [ClientAddressCity], [ClientAddressState], [ClientAddressZip]) VALUES (11, 35, N'12 Rule Them All', N'', N'Elfame', N'DE', N'123456')
INSERT [dbo].[ClientAddresses] ([ClientAddressID], [ClientID], [ClientAddressLine1], [ClientAddressLine2], [ClientAddressCity], [ClientAddressState], [ClientAddressZip]) VALUES (12, 36, N'123 way	way', N'', N'City', N'DE', N'20010')
INSERT [dbo].[ClientAddresses] ([ClientAddressID], [ClientID], [ClientAddressLine1], [ClientAddressLine2], [ClientAddressCity], [ClientAddressState], [ClientAddressZip]) VALUES (13, 36, N'12', N'12', N'12', N'CA', N'121212')
INSERT [dbo].[ClientAddresses] ([ClientAddressID], [ClientID], [ClientAddressLine1], [ClientAddressLine2], [ClientAddressCity], [ClientAddressState], [ClientAddressZip]) VALUES (14, 21, N'test', N'test', N'test', N'AZ', N'11111')
INSERT [dbo].[ClientAddresses] ([ClientAddressID], [ClientID], [ClientAddressLine1], [ClientAddressLine2], [ClientAddressCity], [ClientAddressState], [ClientAddressZip]) VALUES (15, 22, N'lets test', N'test this', N'test city', N'FL', N'123456')
SET IDENTITY_INSERT [dbo].[ClientAddresses] OFF
GO
SET IDENTITY_INSERT [dbo].[Clients] ON 

INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (7, N'UpdateTest', 2, N'1234567891')
INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (8, N'Cindy Rockefeller', 1, N'212-565-4343')
INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (9, N'Robert Mellon', 1, N'412-231-5289')
INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (10, N'Drew Vanderbilt', 1, N'265-438-8512')
INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (11, N'Buck Perot', 1, N'222-222-2222')
INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (12, N'David Musk', 1, N'926-345-8726')
INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (15, N'McTester Industriesss', 2, N'456-123-6789')
INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (17, N'Smith White', 1, N'315-638-9597')
INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (18, N'David Musk', 1, N'926-345-8726')
INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (19, N'Fred Logon', 1, N'323-637-2577')
INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (20, N'Fred Logon', 1, N'323-637-2577')
INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (21, N'Fred Logon', 1, N'323-637-2577')
INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (22, N'UpdateTest', 2, N'4445557878')
INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (23, N'Jim Logon', 1, N'323-637-2577')
INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (26, N'Lord Farquad', 1, N'555-555-5555')
INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (27, N'Spongebob Squarepants', 1, N'111-111-1111')
INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (28, N'Patrick Star', 1, N'222-222-2222')
INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (29, N'Sandy Squirrel', 1, N'333-333-3333')
INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (30, N'Daniel Boone', 1, N'999-888-0000')
INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (32, N'Test ', 1, N'Test')
INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (33, N'Test', 1, N'test')
INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (34, N'TestUPdate', 2, N'5555559595')
INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (35, N'testUpdate', 1, N'1113335454')
INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (36, N'Jason McGoff', 1, N'111-111-1111')
INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (37, N'Another UPdate Test', 1, N'5558887474')
INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (41, N'Clark Logon', 1, N'323-637-2577')
INSERT [dbo].[Clients] ([ClientID], [ClientName], [ClientTypeID], [ClientPhoneNumber]) VALUES (42, N'Check Validation', 1, N'6060606565')
SET IDENTITY_INSERT [dbo].[Clients] OFF
GO
SET IDENTITY_INSERT [dbo].[Flights] ON 

INSERT [dbo].[Flights] ([FlightID], [AircraftID], [PilotID], [StartAirport], [EndAirport], [FlightStartTime], [FlightEndTime]) VALUES (2, 1, 6, 1, 2, CAST(N'2020-02-12T19:58:00' AS SmallDateTime), CAST(N'2020-02-12T22:45:00' AS SmallDateTime))
INSERT [dbo].[Flights] ([FlightID], [AircraftID], [PilotID], [StartAirport], [EndAirport], [FlightStartTime], [FlightEndTime]) VALUES (3, 7, 6, 3, 4, CAST(N'2020-11-04T00:00:00' AS SmallDateTime), CAST(N'2020-11-05T00:00:00' AS SmallDateTime))
INSERT [dbo].[Flights] ([FlightID], [AircraftID], [PilotID], [StartAirport], [EndAirport], [FlightStartTime], [FlightEndTime]) VALUES (4, 1, 1, 1, 2, CAST(N'2020-11-06T00:00:00' AS SmallDateTime), CAST(N'2020-11-13T00:00:00' AS SmallDateTime))
INSERT [dbo].[Flights] ([FlightID], [AircraftID], [PilotID], [StartAirport], [EndAirport], [FlightStartTime], [FlightEndTime]) VALUES (7, 1, 3, 1, 2, CAST(N'2020-12-03T18:44:00' AS SmallDateTime), CAST(N'2020-12-04T18:44:00' AS SmallDateTime))
INSERT [dbo].[Flights] ([FlightID], [AircraftID], [PilotID], [StartAirport], [EndAirport], [FlightStartTime], [FlightEndTime]) VALUES (8, 3, 3, 2, 2, CAST(N'2020-12-11T14:46:00' AS SmallDateTime), CAST(N'2020-12-17T14:46:00' AS SmallDateTime))
INSERT [dbo].[Flights] ([FlightID], [AircraftID], [PilotID], [StartAirport], [EndAirport], [FlightStartTime], [FlightEndTime]) VALUES (9, 4, 5, 2, 3, CAST(N'2020-12-08T14:47:00' AS SmallDateTime), CAST(N'2020-12-18T14:47:00' AS SmallDateTime))
INSERT [dbo].[Flights] ([FlightID], [AircraftID], [PilotID], [StartAirport], [EndAirport], [FlightStartTime], [FlightEndTime]) VALUES (13, 3, 7, 3, 2, CAST(N'2020-12-16T19:42:00' AS SmallDateTime), CAST(N'2020-12-17T19:42:00' AS SmallDateTime))
SET IDENTITY_INSERT [dbo].[Flights] OFF
GO
SET IDENTITY_INSERT [dbo].[LUAircraftStatus] ON 

INSERT [dbo].[LUAircraftStatus] ([AircraftStatusID], [AircraftStatus]) VALUES (1, N'Newly Aquired')
SET IDENTITY_INSERT [dbo].[LUAircraftStatus] OFF
GO
SET IDENTITY_INSERT [dbo].[LUClientType] ON 

INSERT [dbo].[LUClientType] ([ClientTypeID], [ClientType]) VALUES (1, N'Private')
INSERT [dbo].[LUClientType] ([ClientTypeID], [ClientType]) VALUES (2, N'Corporate')
SET IDENTITY_INSERT [dbo].[LUClientType] OFF
GO
SET IDENTITY_INSERT [dbo].[LUShipmentStatus] ON 

INSERT [dbo].[LUShipmentStatus] ([ShipmentStatusID], [ShipmentStatus]) VALUES (1, N'Received')
INSERT [dbo].[LUShipmentStatus] ([ShipmentStatusID], [ShipmentStatus]) VALUES (2, N'In-Transit')
INSERT [dbo].[LUShipmentStatus] ([ShipmentStatusID], [ShipmentStatus]) VALUES (3, N'Delivered')
SET IDENTITY_INSERT [dbo].[LUShipmentStatus] OFF
GO
SET IDENTITY_INSERT [dbo].[Pilots] ON 

INSERT [dbo].[Pilots] ([PilotID], [FirstName], [LastName], [DateOfBirth], [EmployeeNumber], [DateOFHire], [DateLeftCAF]) VALUES (1, N'Gary', N'Wayne', CAST(N'1955-04-20' AS Date), N'B1GGD4DD7', CAST(N'2000-01-01' AS Date), NULL)
INSERT [dbo].[Pilots] ([PilotID], [FirstName], [LastName], [DateOfBirth], [EmployeeNumber], [DateOFHire], [DateLeftCAF]) VALUES (3, N'Bubba', N'Gump', CAST(N'1960-03-15' AS Date), N'34TG00D', CAST(N'2012-04-26' AS Date), NULL)
INSERT [dbo].[Pilots] ([PilotID], [FirstName], [LastName], [DateOfBirth], [EmployeeNumber], [DateOFHire], [DateLeftCAF]) VALUES (4, N'Dan', N'Bland', CAST(N'1975-09-30' AS Date), N'BL4NDM4N', CAST(N'2008-06-10' AS Date), NULL)
INSERT [dbo].[Pilots] ([PilotID], [FirstName], [LastName], [DateOfBirth], [EmployeeNumber], [DateOFHire], [DateLeftCAF]) VALUES (5, N'Nick', N'Irving', CAST(N'1986-12-13' AS Date), N'R34P3R', CAST(N'2015-06-25' AS Date), NULL)
INSERT [dbo].[Pilots] ([PilotID], [FirstName], [LastName], [DateOfBirth], [EmployeeNumber], [DateOFHire], [DateLeftCAF]) VALUES (6, N'Joe', N'Schmoe', CAST(N'1982-08-03' AS Date), N'L0S3R', CAST(N'2000-02-04' AS Date), NULL)
INSERT [dbo].[Pilots] ([PilotID], [FirstName], [LastName], [DateOfBirth], [EmployeeNumber], [DateOFHire], [DateLeftCAF]) VALUES (7, N'Freddy', N'Right', CAST(N'1969-12-31' AS Date), N'345TGHT', CAST(N'1969-12-31' AS Date), NULL)
INSERT [dbo].[Pilots] ([PilotID], [FirstName], [LastName], [DateOfBirth], [EmployeeNumber], [DateOFHire], [DateLeftCAF]) VALUES (8, N'Freddy', N'Right', CAST(N'1969-12-31' AS Date), N'345TGHT', CAST(N'1969-12-31' AS Date), NULL)
INSERT [dbo].[Pilots] ([PilotID], [FirstName], [LastName], [DateOfBirth], [EmployeeNumber], [DateOFHire], [DateLeftCAF]) VALUES (9, N'Dolly', N'Parton', CAST(N'1946-01-19' AS Date), N'D0LLYw00d', CAST(N'2020-11-20' AS Date), CAST(N'2020-12-25' AS Date))
SET IDENTITY_INSERT [dbo].[Pilots] OFF
GO
SET IDENTITY_INSERT [dbo].[Shipments] ON 

INSERT [dbo].[Shipments] ([ShipmentID], [ClientID], [ShipmentVolume], [ShipmentWeight], [ShipmentStatusID], [ShipmentStartDate], [ShipmentEndDate], [ShipmentNotes]) VALUES (2, 8, 123.5, 23, 2, CAST(N'2020-12-16' AS Date), CAST(N'2020-12-18' AS Date), N'test names')
INSERT [dbo].[Shipments] ([ShipmentID], [ClientID], [ShipmentVolume], [ShipmentWeight], [ShipmentStatusID], [ShipmentStartDate], [ShipmentEndDate], [ShipmentNotes]) VALUES (4, 11, 90.55999755859375, 77.7699966430664, 2, CAST(N'2020-12-02' AS Date), CAST(N'2020-12-08' AS Date), N'adding to NEW NEW')
INSERT [dbo].[Shipments] ([ShipmentID], [ClientID], [ShipmentVolume], [ShipmentWeight], [ShipmentStatusID], [ShipmentStartDate], [ShipmentEndDate], [ShipmentNotes]) VALUES (5, 10, 123, 123, 2, CAST(N'2020-12-15' AS Date), CAST(N'2020-12-19' AS Date), N'testone')
INSERT [dbo].[Shipments] ([ShipmentID], [ClientID], [ShipmentVolume], [ShipmentWeight], [ShipmentStatusID], [ShipmentStartDate], [ShipmentEndDate], [ShipmentNotes]) VALUES (7, 9, 123.5, 123.5, 2, CAST(N'2020-12-16' AS Date), CAST(N'2020-12-18' AS Date), N'test floats')
INSERT [dbo].[Shipments] ([ShipmentID], [ClientID], [ShipmentVolume], [ShipmentWeight], [ShipmentStatusID], [ShipmentStartDate], [ShipmentEndDate], [ShipmentNotes]) VALUES (8, 8, 123.5, 23, 2, CAST(N'2020-12-16' AS Date), CAST(N'2020-12-18' AS Date), N'test names')
INSERT [dbo].[Shipments] ([ShipmentID], [ClientID], [ShipmentVolume], [ShipmentWeight], [ShipmentStatusID], [ShipmentStartDate], [ShipmentEndDate], [ShipmentNotes]) VALUES (9, 37, 1123, 123, 2, CAST(N'2020-12-03' AS Date), CAST(N'2020-12-05' AS Date), N'testtesttest')
SET IDENTITY_INSERT [dbo].[Shipments] OFF
GO
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([UserID], [UserName], [PasswordHash]) VALUES (3, N'Admin', 0x887375DAEC62A9F02D32A63C9E14C7641A9A8A42E4FA8F6590EB928D9744B57BB5057A1D227E4D40EF911AC030590BBCE2BFDB78103FF0B79094CEE8425601F5)
SET IDENTITY_INSERT [dbo].[Users] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Users__C9F28456202DDD31]    Script Date: 12/16/2020 6:20:34 PM ******/
ALTER TABLE [dbo].[Users] ADD UNIQUE NONCLUSTERED 
(
	[UserName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Aircraft]  WITH CHECK ADD  CONSTRAINT [FK_Aircraft_AircraftModels] FOREIGN KEY([ACModelID])
REFERENCES [dbo].[AircraftModels] ([ACModelID])
GO
ALTER TABLE [dbo].[Aircraft] CHECK CONSTRAINT [FK_Aircraft_AircraftModels]
GO
ALTER TABLE [dbo].[Aircraft]  WITH CHECK ADD  CONSTRAINT [FK_Aircraft_LUAircraftStatus] FOREIGN KEY([AircraftStatusID])
REFERENCES [dbo].[LUAircraftStatus] ([AircraftStatusID])
GO
ALTER TABLE [dbo].[Aircraft] CHECK CONSTRAINT [FK_Aircraft_LUAircraftStatus]
GO
ALTER TABLE [dbo].[CargoManifests]  WITH CHECK ADD  CONSTRAINT [FK_CargoManifests_Flights] FOREIGN KEY([FlightID])
REFERENCES [dbo].[Flights] ([FlightID])
GO
ALTER TABLE [dbo].[CargoManifests] CHECK CONSTRAINT [FK_CargoManifests_Flights]
GO
ALTER TABLE [dbo].[CargoManifests]  WITH CHECK ADD  CONSTRAINT [FK_CargoManifests_Shipments] FOREIGN KEY([ShipmentID])
REFERENCES [dbo].[Shipments] ([ShipmentID])
GO
ALTER TABLE [dbo].[CargoManifests] CHECK CONSTRAINT [FK_CargoManifests_Shipments]
GO
ALTER TABLE [dbo].[ClientAddresses]  WITH CHECK ADD  CONSTRAINT [FK_ClientAddresses_Clients] FOREIGN KEY([ClientID])
REFERENCES [dbo].[Clients] ([ClientID])
GO
ALTER TABLE [dbo].[ClientAddresses] CHECK CONSTRAINT [FK_ClientAddresses_Clients]
GO
ALTER TABLE [dbo].[Clients]  WITH CHECK ADD  CONSTRAINT [FK_Clients_LUClientType] FOREIGN KEY([ClientTypeID])
REFERENCES [dbo].[LUClientType] ([ClientTypeID])
GO
ALTER TABLE [dbo].[Clients] CHECK CONSTRAINT [FK_Clients_LUClientType]
GO
ALTER TABLE [dbo].[Flights]  WITH CHECK ADD  CONSTRAINT [FK_Flights_Aircraft] FOREIGN KEY([AircraftID])
REFERENCES [dbo].[Aircraft] ([AircraftID])
GO
ALTER TABLE [dbo].[Flights] CHECK CONSTRAINT [FK_Flights_Aircraft]
GO
ALTER TABLE [dbo].[Flights]  WITH CHECK ADD  CONSTRAINT [FK_Flights_Airports_End] FOREIGN KEY([EndAirport])
REFERENCES [dbo].[Airports] ([AirportID])
GO
ALTER TABLE [dbo].[Flights] CHECK CONSTRAINT [FK_Flights_Airports_End]
GO
ALTER TABLE [dbo].[Flights]  WITH CHECK ADD  CONSTRAINT [FK_Flights_Airports_Start] FOREIGN KEY([StartAirport])
REFERENCES [dbo].[Airports] ([AirportID])
GO
ALTER TABLE [dbo].[Flights] CHECK CONSTRAINT [FK_Flights_Airports_Start]
GO
ALTER TABLE [dbo].[Flights]  WITH CHECK ADD  CONSTRAINT [FK_Flights_Pilots] FOREIGN KEY([PilotID])
REFERENCES [dbo].[Pilots] ([PilotID])
GO
ALTER TABLE [dbo].[Flights] CHECK CONSTRAINT [FK_Flights_Pilots]
GO
ALTER TABLE [dbo].[Shipments]  WITH CHECK ADD  CONSTRAINT [FK_Shipments_Clients] FOREIGN KEY([ClientID])
REFERENCES [dbo].[Clients] ([ClientID])
GO
ALTER TABLE [dbo].[Shipments] CHECK CONSTRAINT [FK_Shipments_Clients]
GO
ALTER TABLE [dbo].[Shipments]  WITH CHECK ADD  CONSTRAINT [FK_Shipments_LUShipmentStatus] FOREIGN KEY([ShipmentStatusID])
REFERENCES [dbo].[LUShipmentStatus] ([ShipmentStatusID])
GO
ALTER TABLE [dbo].[Shipments] CHECK CONSTRAINT [FK_Shipments_LUShipmentStatus]
GO
/****** Object:  StoredProcedure [dbo].[Add_Aircraft]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*Champlain Air Freight - Add_Aircraft
*Author - Kelly May
*Date - Oct 24, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored procedure for adding a new aircraft into the database. User inputs an existing
* ACModelID for the new aircraft, and the procedure creates a new AircraftID and sets the 
* AircraftStatusID to 1. 
*
*/

CREATE PROCEDURE [dbo].[Add_Aircraft]

@ACModelID int
AS
BEGIN

INSERT INTO dbo.Aircraft (ACModelID, AircraftStatusID)
VALUES (@ACModelID, '1')

END
GO
/****** Object:  StoredProcedure [dbo].[Add_Aircraft_Model]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Add_Aircraft_Model
*Author - Kelly May
*Date - Oct 24, 2020 
*Date Last Modified - Nov 5, 2020
*Last Modified By - Andrew Dockan	
*Changes Made - updated varchar fields to accept more characters
*Description - Stored procedure for adding a new aircraft model into the database. User inputs values for
*ACMake, ACModel, ACRange, ACRangeClassification, ACPayload, and ACLoadVolume. ACModelID will be automatically 
*generated
*
*/

CREATE PROCEDURE [dbo].[Add_Aircraft_Model]

@ACMake varchar(20), 
@ACModel varchar(20),
@ACRange varchar(20),
@ACRangeClassification varchar(20),
@ACPayload float, 
@ACLoadVolume float

AS
BEGIN

INSERT INTO dbo.AircraftModels (ACMake, ACModel, ACRange, ACRangeClassification, ACPayload, ACLoadVolume)
VALUES (@ACMake, @ACModel, @ACRange, @ACRangeClassification, @ACPayload, @ACLoadVolume)

END
GO
/****** Object:  StoredProcedure [dbo].[Add_Airport]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*Champlain Air Freight - Add_Airport
*Author - Andrew Dockan
*Date - Nov 10, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure adds new airport to the airports table
*
*/

Create Procedure [dbo].[Add_Airport]
	(
	@AirportName varchar(20),
	@AirportLocation varchar(20),
	@AirportHub bit,
	@AirportDistanceFromHub float
	)
As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	Insert Into Airports(AirportName, AirportLocation, AirportHub, AirportDistanceFromHub)
		Values (@AirportName, @AirportLocation, @AirportHub, @AirportDistanceFromHub); 

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[Add_Client]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Add_Client
*Author - Andrew Dockan
*Date - Oct 28, 2020 
*Date Last Modified - Nov 10, 2020
*Last Modified By - Andrew Dockan
*Changes Made - updating phonenumber field to varchar
*Description - Stored Procedure adds new client to clients table
*
*/

CREATE Procedure [dbo].[Add_Client]
	(
	@ClientName varchar(30),
	@ClientTypeID int,
	@ClientPhoneNumber varchar(20)
	)
As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	Insert Into Clients(ClientName, ClientTypeID, ClientPhoneNumber)
		Values (@ClientName, @ClientTypeID, @ClientPhoneNumber); 

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[Add_Client_Address]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*Champlain Air Freight - Add_Client_Address
*Author - Andrew Dockan
*Date - Oct 30, 2020 
*Date Last Modified - 12/10/2020
*Last Modified By - Andrew Dockan
*Changes Made - updating change to zip from int to varchar to include leading zeros
*Description - Stored Procedure adds new alient address to clientaddresses table
*
*/

CREATE Procedure [dbo].[Add_Client_Address]
	(
	@ClientID int,
	@ClientAddressLine1 varchar(120),
	@ClientAddressLine2 varchar(120),
	@ClientAddressCity varchar(50),
	@ClientAddressState varchar(2),
	@ClientAddressZip varchar(10)
	)
As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	Insert Into ClientAddresses(ClientID, ClientAddressLine1, ClientAddressLine2, ClientAddressCity, ClientAddressState, ClientAddressZip)
		Values (@ClientID, @ClientAddressLine1, @ClientAddressLine2, @ClientAddressCity, @ClientAddressState, @ClientAddressZip); 

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[Add_Flight]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Add_Flight
*Author - Andrew Dockan
*Date - Oct 28, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure adds new flight to flights table
*
*/

Create Procedure [dbo].[Add_Flight]
	(
	@AircraftID int,
	@PilotID int,
	@StartAirport int,
	@EndAirport int,
	@FlightStartTime smalldatetime = null,
	@FlightEndTime smalldatetime = null
	)
As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	Insert Into Flights(AircraftID, PilotID, StartAirport, EndAirport, FlightStartTime, FlightEndTime)
		Values (@AircraftID, @PilotID, @StartAirport, @EndAirport, @FlightStartTime, @FlightEndTime); 

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[Add_Pilot]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Add_Pilot
*Author - Kelly May
*Date - Oct 24, 2020 
*Date Last Modified - Nov 5, 2020
*Last Modified By - Andrew Dockan	
*Changes Made - updated varchar fields to accept more characters
*Description - Stored Procedure adds FirstName, LastName, DateOfBirth, EmployeeNumber, DateOfHire information 
* into the Pilots table. DateLeftCaf is left NULL, and PilotID is autogenerated. 
*
*/

CREATE PROCEDURE [dbo].[Add_Pilot]

@FirstName varchar(20), 
@LastName varchar(30), 
@DateOfBirth date, 
@EmployeeNumber varchar(20), 
@DateOfHire date

AS
BEGIN
BEGIN TRANSACTION

INSERT INTO Pilots (FirstName, LastName, DateOfBirth, EmployeeNumber, DateOFHire)
Values (@FirstName, @LastName, @DateOfBirth, @EmployeeNumber, @DateOfHire) 

COMMIT
END
GO
/****** Object:  StoredProcedure [dbo].[Add_Shipment]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Add_Shipment
*Author - Matt Ridgway
*Date - Oct 23, 2020 
*Date Last Modified - Andrew Dockan 
*Last Modified By - Nov 12, 2020
*Changes Made - removed shipmentid as required input, 
*	since this is an initial add and shipment id is autogenerated. 
*	Also removed input error handling, errored out on valid data
*Description - Adds a Shipment into dbo.Shipments . 
*	I tried to write in error catching
*/

CREATE PROCEDURE [dbo].[Add_Shipment](
	@ClientID int,
	@ShipmentVolume FLOAT,
	@ShipmentWeight FLOAT,
	@ShipmentStatusID int,
	@ShipmentStartDate date,
	@ShipmentEndDate date,
	@ShipmentNotes VARCHAR(1000) 
) 
AS

SET NOCOUNT ON;
BEGIN
	DECLARE
	@TempClientID int,
	@TempShipID int,
	@NewClientID int,	
	@Volume FLOAT,
	@Weight FLOAT,
	@StatusID int,
	@StartDate date,
	@EndDate date,
	@Notes VARCHAR(1000),
	@Succ bit,
	@ErrorMsg NVarchar(50)
	

	SET @Succ =0;
	SET @ErrorMsg ='';
	SET @TempClientID= 0;
BEGIN TRY
	BEGIN TRANSACTION;
	
		INSERT dbo.Shipments (ClientID,ShipmentVolume,ShipmentWeight,ShipmentStatusID,ShipmentStartDate,ShipmentEndDate,ShipmentNotes)
     			VALUES (@ClientID,@ShipmentVolume,@ShipmentWeight,@ShipmentStatusID,@ShipmentStartDate,@ShipmentEndDate,@ShipmentNotes)
         
				set @NewClientID =@@IDENTITY;
				set @succ =1;
				set @ErrorMsg =0;
				COMMIT TRANSACTION;
END TRY

		BEGIN CATCH
			ROLLBACK TRANSACTION;
			 IF @@ERROR <> 50000
					BEGIN
						SET @ErrorMsg = @ErrorMsg + CONVERT(nVarchar(50), @@Error);
					END
			   	
			 
			 SELECT @ErrorMsg =@ErrorMsg + ' -7001' +' Insert Failed';
			 SET @Succ =0;
			 
			 RAISERROR(@ErrorMsg, 16, 1);
			 	 
		
		END CATCH
END
GO
/****** Object:  StoredProcedure [dbo].[Add_Shipment_Status]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*Champlain Air Freight - Add_Shipment_Status
*Author - Andrew Dockan
*Date - Nov 11, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure adds new shipment status to the shipment status lookup table
*
*/

Create Procedure [dbo].[Add_Shipment_Status]
	(
	@ShipmentStatus varchar(20)	
	)
As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	Insert Into LUShipmentStatus(ShipmentStatus)
		Values (@ShipmentStatus); 

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[Add_Shipment_To_Cargo_Manifest]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Add_Shipment_To_Cargo_Manifest
*Author - Matt Ridgway
*Date - Oct 23, 2020 
*Date Last Modified - Nov 12, 2020
*Last Modified By - Andrew Dockan
*Changes Made - added flight id info, removed error handling, errored on valid data
*Description - Insert a Shipment for dbo.CargoManifests
*/

CREATE PROCEDURE [dbo].[Add_Shipment_To_Cargo_Manifest](
	@ShipmentID int,
	@FlightID int
)
AS
SET NOCOUNT ON;
BEGIN	
	
	BEGIN TRANSACTION;
	
		INSERT INTO dbo.CargoManifests (ShipmentID, FlightID)
     		VALUES (@ShipmentID, @FlightID);
         		
	COMMIT TRANSACTION;
	   		
END
GO
/****** Object:  StoredProcedure [dbo].[Add_User]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Add_User
*Author - Andrew Dockan
*Date - Oct 28, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure adds new user to users table
*
*/

Create Procedure [dbo].[Add_User]
	(
	@UserName varchar(50),
	@Password varchar(50)
	)
As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	Insert Into Users(UserName, PasswordHash)
		Values (@UserName, HASHBYTES('SHA2_512',@Password)); 

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[Delete_Aircraft]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Delete_Aircraft
*Author - Kelly May
*Date - Oct 24, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure deletes an aircraft table entry that matches the given AircraftID
*
*/

CREATE PROCEDURE [dbo].[Delete_Aircraft]

@AircraftID int

AS
BEGIN

DELETE FROM Aircraft
WHERE AircraftID = @AircraftID

END
GO
/****** Object:  StoredProcedure [dbo].[Delete_Aircraft_Model]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*Champlain Air Freight - Delete_Aircraft_Model
*Author - Andrew Dockan
*Date - Nov 10, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure deletes an aircraft model from the aircraftmodels table
*
*/

Create Procedure [dbo].[Delete_Aircraft_Model]
	(
	@ACModelID int
	)

As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	Delete From AircraftModels
		Where ACModelID = @ACModelID;

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[Delete_Airport]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Delete_Airport
*Author - Andrew Dockan
*Date - Nov 10, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure deletes airport from airports table
*
*/

Create Procedure [dbo].[Delete_Airport]
	(
	@AirportID int
	)

As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	Delete From Airports
		Where AirportID = @AirportID;

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[Delete_Client]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Delete_Client
*Author - Andrew Dockan
*Date - Oct 28, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure deletes client from clients table
*
*/

Create Procedure [dbo].[Delete_Client]
	(
	@ClientID int
	)

As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	Delete From Clients
		Where ClientID = @ClientID;

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[Delete_Client_Address]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Delete_Client_Address
*Author - Andrew Dockan
*Date - Oct 30, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure deletes client address from clientaddresses table
*
*/

Create Procedure [dbo].[Delete_Client_Address]
	(
	@ClientAddressID int
	)

As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	Delete From ClientAddresses
		Where ClientAddressID = @ClientAddressID;

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[Delete_Flight]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Delete_Flight
*Author - Andrew Dockan
*Date - Oct 28, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure deletes flight from flights table
*
*/

Create Procedure [dbo].[Delete_Flight]
	(
	@FlightID int
	)

As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	Delete From Flights
		Where FlightID = @FlightID;

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[Delete_Pilot]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Delete_Pilot
*Author - Kelly May
*Date - Oct 24, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - deletes all pilot information for a given pilot, based on the PilotID
*
*/

CREATE PROCEDURE [dbo].[Delete_Pilot]
@PilotID int

AS
BEGIN
BEGIN TRANSACTION

DELETE FROM Pilots
WHERE PilotID = @PilotID

COMMIT
END
GO
/****** Object:  StoredProcedure [dbo].[Delete_Shipment]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Add_Shipment_To_Cargo_Manifest
*Author - Matt Ridgway
*Date - Oct 23, 2020 
*Date Last Modified - Nov 12, 2020
*Last Modified By - Andrew Dockan
*Changes Made - removed error handling, errored on valid data
*Description - Deletes a Shipment from dbo.Shipments
*/
CREATE PROCEDURE [dbo].[Delete_Shipment](
	@ShipmentID INT
)
AS

SET NOCOUNT ON;
		
	BEGIN
		
		BEGIN TRANSACTION;
				
			DELETE FROM dbo.Shipments
				WHERE ShipmentID = @ShipmentID
						
		COMMIT TRANSACTION;
		
	END
GO
/****** Object:  StoredProcedure [dbo].[Delete_Shipment_From_Cargo_Manifests]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Delete_Shipment_From_Cargo_Manifests
*Author - Matt Ridgway
*Date - Oct 23, 2020 
*Date Last Modified - Nov 12, 2020
*Last Modified By - Andrew Dockan
*Changes Made - removed error handling, errored on valid data, 
*	corrected to delete from cargomanifests table and not shipments table
*Description - Deletes a Shipment from dbo.CargoManifests
*/
CREATE PROCEDURE [dbo].[Delete_Shipment_From_Cargo_Manifests](
	@ShipmentID INT
)
AS
SET NOCOUNT ON;
BEGIN
		BEGIN TRANSACTION;
				
				DELETE FROM dbo.CargoManifests
					WHERE ShipmentID = @ShipmentID
						
		COMMIT TRANSACTION;
END
GO
/****** Object:  StoredProcedure [dbo].[Delete_Shipment_Status]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Delete_Shipment_Status
*Author - Andrew Dockan
*Date - Nov 11, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure deletes shipment status from the shipment status lookup table
*
*/

Create Procedure [dbo].[Delete_Shipment_Status]
	(
	@ShipmentStatusID int
	)

As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	Delete From LUShipmentStatus
		Where ShipmentStatusID = @ShipmentStatusID;

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[Delete_User]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Delete_User
*Author - Andrew Dockan
*Date - Oct 28, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure deletes user from users table
*
*/

Create Procedure [dbo].[Delete_User]
	(
	@UserID int
	)

As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	Delete From Users
		Where UserID = @UserID;

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[Update_Aircraft]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Update_Aircraft
*Author - Kelly May
*Date - Oct 24, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure allows user to make changes to the ACModelID for a given AircraftID in the aircraft database. 
*
*/

CREATE PROCEDURE [dbo].[Update_Aircraft]

@AircraftID int,
@ACModelID int

AS
BEGIN

UPDATE Aircraft 
SET
ACModelID = @ACModelID
WHERE AircraftID = @AircraftID
END
GO
/****** Object:  StoredProcedure [dbo].[Update_Airport]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Update_Airport
*Author - Andrew Dockan
*Date - Nov 10, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure updates Airport in Airports table with provided values
*
*/

Create Procedure [dbo].[Update_Airport]
	(
	@AirportID int,
	@AirportName varchar(20),
	@AirportLocation varchar(20),
	@AirportHub bit,
	@AirportDistanceFromHub float
	)
As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	If(@AirportName is not null)
	Update Airports
	Set AirportName = @AirportName
	Where AirportID = @AirportID
	;

	If(@AirportLocation is not null)
	Update Airports
	Set AirportLocation = @AirportLocation
	Where AirportID = @AirportID	
	;

	If(@AirportHub is not null)
	Update Airports
	Set AirportHub = @AirportHub
	Where AirportID = @AirportID
	;

	If(@AirportDistanceFromHub is not null)
	Update Airports
	Set AirportDistanceFromHub = @AirportDistanceFromHub
	Where AirportID = @AirportID
	;

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[Update_Client]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Update_Client
*Author - Andrew Dockan
*Date - Oct 28, 2020 
*Date Last Modified - Nov 15, 2020
*Last Modified By - Andrew Dockan
*Changes Made - Update phone number field to varchar from int
*Description - Stored Procedure updates client in clients table with specified info
*
*/

CREATE Procedure [dbo].[Update_Client]
	(
	@ClientID int,
	@ClientName varchar(30) = null,
	@ClientTypeID int = null,
	@ClientPhoneNumber varchar(20) = null
	)
As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	If(@ClientName is not null)
	Update Clients
	Set ClientName = @ClientName
	Where ClientID = @ClientID
	;

	If(@ClientTypeID is not null)
	Update Clients
	Set ClientTypeID = @ClientTypeID
	Where ClientID = @ClientID
	;

	If(@ClientPhoneNumber is not null)
	Update Clients
	Set ClientPhoneNumber = @ClientPhoneNumber
	Where ClientID = @ClientID
	;

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[Update_Client_Address]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*Champlain Air Freight - Update_Client_Address
*Author - Andrew Dockan
*Date - Oct 30, 2020 
*Date Last Modified - 12/10/2020
*Last Modified By - Andrew Dockan
*Changes Made - updating change to zip from int to varchar to include leading zeros
*Description - Stored Procedure updates client address in client addresses table with provided values
*
*/

CREATE Procedure [dbo].[Update_Client_Address]
	(
	@ClientAddressID int,
	@ClientID int = null,
	@ClientAddressLine1 varchar(120) = null,
	@ClientAddressLine2 varchar(120) = null,
	@ClientAddressCity varchar(50) = null,
	@ClientAddressState varchar(2) = null,
	@ClientAddressZip varchar(10) = null
	)
As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	If(@ClientID is not null)
	Update ClientAddresses
	Set ClientID = @ClientID
	Where ClientAddressID = @ClientAddressID
	;

	If(@ClientAddressLine1 is not null)
	Update ClientAddresses
	Set ClientAddressLine1 = @ClientAddressLine1
	Where ClientAddressID = @ClientAddressID	
	;

	If(@ClientAddressLine2 is not null)
	Update ClientAddresses
	Set ClientAddressLine2 = @ClientAddressLine2
	Where ClientAddressID = @ClientAddressID
	;

	If(@ClientAddressCity is not null)
	Update ClientAddresses
	Set ClientAddressCity = @ClientAddressCity
	Where ClientAddressID = @ClientAddressID
	;

	If(@ClientAddressState is not null)
	Update ClientAddresses
	Set ClientAddressState = @ClientAddressState
	Where ClientAddressID = @ClientAddressID
	;

	If(@ClientAddressZip is not null)
	Update ClientAddresses
	Set ClientAddressZip = @ClientAddressZip
	Where ClientAddressID = @ClientAddressID
	;

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[Update_Flight]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Update_Flight
*Author - Andrew Dockan
*Date - Oct 28, 2020 
*Date Last Modified - Oct 30, 2020
*Last Modified By - Andrew Dockan
*Changes Made - updated IF statements to match the desired fields to be updated
*Description - Stored Procedure updates flight in flights table with provided values
*
*/

Create Procedure [dbo].[Update_Flight]
	(
	@FlightID int,
	@AircraftID int = null,
	@PilotID int = null,
	@StartAirport int = null,
	@EndAirport int = null,
	@FlightStartTime smalldatetime = null,
	@FlightEndTime smalldatetime = null
	)
As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	If(@AircraftID is not null)
	Update Flights
	Set AircraftID = @AircraftID
	Where FlightID = @FlightID
	;

	If(@PilotID is not null)
	Update Flights
	Set PilotID = @PilotID
	Where FlightID = @FlightID
	;

	If(@StartAirport is not null)
	Update Flights
	Set StartAirport = @StartAirport
	Where FlightID = @FlightID
	;

	If(@EndAirport is not null)
	Update Flights
	Set EndAirport = @EndAirport
	Where FlightID = @FlightID
	;

	If(@FlightStartTime is not null)
	Update Flights
	Set FlightStartTime = @FlightStartTime
	Where FlightID = @FlightID
	;

	If(@FlightEndTime is not null)
	Update Flights
	Set FlightEndTime = @FlightEndTime
	Where FlightID = @FlightID
	;

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[Update_Pilot]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Update_Pilot
*Author - Kelly May
*Date - Oct 24, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Updates an individual Pilot's information based on a given PilotID. User can 
* change the FirstName, LastName, DateOfBirth, EmployeeNumber, DateOfHire, and DateLeftCAF fields. 
*
*/

CREATE PROCEDURE [dbo].[Update_Pilot]
@PilotID int, 
@FirstName varchar(20), 
@LastName varchar(30), 
@DateOfBirth date, 
@EmployeeNumber varchar(20), 
@DateOfHire date, 
@DateLeftCAF date

AS
BEGIN
BEGIN TRANSACTION

UPDATE Pilots 
SET
FirstName = @FirstName, 
LastName = @LastName, 
DateOfBirth = @DateOfBirth, 
EmployeeNumber = @EmployeeNumber, 
DateOFHire = @DateOfHire, 
DateLeftCAF = @DateLeftCAF
WHERE PilotID = @PilotID

COMMIT
END
GO
/****** Object:  StoredProcedure [dbo].[Update_Shipment]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Update_Shipment
*Author - Matt Ridgway
*Date - Oct 23, 2020 
*Date Last Modified - Nov 15, 2020
*Last Modified By - Andrew Dockan
*Changes Made - update input fields to accept null, 
*		removed error handling, errored on valid input
*		updated input logic
*Description - Updatesa Shipment into dbo. Shipments I tried to write in error catching
*/

CREATE PROCEDURE [dbo].[Update_Shipment] (
	@ShipmentID int,
	@ClientID int = null,
	@ShipmentVolume FLOAT = null,
	@ShipmentWeight FLOAT = null,
	@ShipmentStatusID int = null,
	@ShipmentStartDate date = null,
	@ShipmentEndDate date = null,
	@ShipmentNotes VARCHAR(1000) = null
) 
AS

SET NOCOUNT ON;
BEGIN

	BEGIN TRANSACTION;		
		
		If(@ClientID is not null)
		Update Shipments
		Set ClientID = @ClientID
		Where ShipmentID = @ShipmentID
		;

		If(@ShipmentVolume is not null)
		Update Shipments
		Set ShipmentVolume = @ShipmentVolume
		Where ShipmentID = @ShipmentID
		;

		If(@ShipmentWeight is not null)
		Update Shipments
		Set ShipmentWeight = @ShipmentWeight
		Where ShipmentID = @ShipmentID
		;

		If(@ShipmentStatusID is not null)
		Update Shipments
		Set ShipmentStatusID = @ShipmentStatusID
		Where ShipmentID = @ShipmentID
		;

		If(@ShipmentStartDate is not null)
		Update Shipments
		Set ShipmentStartDate = @ShipmentStartDate
		Where ShipmentID = @ShipmentID
		;

		If(@ShipmentEndDate is not null)
		Update Shipments
		Set ShipmentEndDate = @ShipmentEndDate
		Where ShipmentID = @ShipmentID
		;

		If(@ShipmentNotes is not null)
		Update Shipments
		Set ShipmentNotes = @ShipmentNotes
		Where ShipmentID = @ShipmentID
		;
			
     			
	COMMIT TRANSACTION;		

END
GO
/****** Object:  StoredProcedure [dbo].[Update_Shipment_Status]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Update_Shipment_Status
*Author - Andrew Dockan
*Date - Nov 10, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure updates a shipment status in the shipment status lookup table with provided values
*
*/

Create Procedure [dbo].[Update_Shipment_Status]
	(
	@ShipmentStatusID int,
	@ShipmentStatus varchar(20)
	)
As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	If(@ShipmentStatus is not null)
	Update LUShipmentStatus
	Set ShipmentStatus = @ShipmentStatus
	Where ShipmentStatusID = @ShipmentStatusID
	;

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[Update_User_Password]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Update_User_Password
*Author - Andrew Dockan
*Date - Oct 28, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure updates a user's password in the users table
*
*/

Create Procedure [dbo].[Update_User_Password]
	(
	@UserID int,
	@Password varchar(50) = null,
	@NewPassword varchar(50) = null
	)
As
Begin
	
	Set NoCount On;
	
	Declare @ValidPassword int;
	
	Begin Transaction;
	
	Set @ValidPassword = 
		(
		Select UserID 
		From Users 
		Where UserID = @UserID and 
			PasswordHash = HASHBYTES('SHA2_512',@Password)
		)

	If(@ValidPassword is not null)
		Update Users
		Set PasswordHash = HASHBYTES('SHA2_512',@NewPassword)
		
	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[Update_User_UserName]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Update_User_UserName
*Author - Andrew Dockan
*Date - Oct 28, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure updates a user's username in the users table
*
*/

Create Procedure [dbo].[Update_User_UserName]
	(
	@UserID int,
	@UserName varchar(50)	
	)
As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	If(@UserName is not null)
	Update Users
	Set UserName = @UserName
	Where UserID = @UserID
	;

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[Validate_User_Password]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Validate_User_Password
*Author - Andrew Dockan
*Date - Nov 29, 2020 
*Date Last Modified - Dec 4, 2020
*Last Modified By - Andrew Dockan
*Changes Made - UserName has changed to a unique index for the users table, so it can be used instead of the userid,
*	modifying this procedure to reflect that and make it easier for the main program to use
*Description - Stored Procedure that validate's if a password is valid, 
*	returns bit as boolean, 1 for true, 0 for false
*
*/

CREATE Procedure [dbo].[Validate_User_Password]
	(
	@UserName varchar(50),
	@Password varchar(50),
	@ValidPassword int = null OUTPUT 
	)
As
Begin
	
	Set NoCount On;

	DECLARE @TempUserID int;
	
	Set @TempUserID = 
		(
		Select UserID 
		From Users 
		Where UserName = @UserName and 
			PasswordHash = HASHBYTES('SHA2_512',@Password)
		)

	If(@TempUserID is not null)
		Set @ValidPassword = 1;
	Else
		Set @ValidPassword = 0;
		
	Return
	
	Set NoCount Off;

End
;
GO
/****** Object:  StoredProcedure [dbo].[View_All_Aircraft]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - View_All_Aircraft
*Author - Kelly May
*Date - Oct 24, 2020 
*Date Last Modified - Dec 13, 2020
*Last Modified By - Andrew Dockan
*Changes Made - updated output for easier use with java program
*Description - Stored Procedure allows user to view Aircraft table, joined with the AircraftModels table
*
*/

CREATE PROCEDURE [dbo].[View_All_Aircraft]

AS
BEGIN

SELECT ac.AircraftID, ac.ACModelID, luacs.AircraftStatus, 
	acm.ACMake, acm.ACModel, acm.ACRange, 
	acm.ACRangeClassification, acm.ACPayload, acm.ACLoadVolume  
FROM Aircraft ac
LEFT JOIN AircraftModels acm ON ac.ACModelID = acm.ACModelID
LEFT JOIN LUAircraftStatus luacs on ac.AircraftStatusID = luacs.AircraftStatusID

END
GO
/****** Object:  StoredProcedure [dbo].[View_All_Aircraft_Model]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - View_All_Aircraft_Model
*Author - Andrew Dockan
*Date - Dec 13, 2020
*Date Last Modified - 
*Last Modified By - 
*Changes Made -
*Description - Stored Procedure allows user to view Aircraft Models table
*
*/

CREATE PROCEDURE [dbo].[View_All_Aircraft_Model]

AS
BEGIN

SELECT * 
FROM AircraftModels

END
GO
/****** Object:  StoredProcedure [dbo].[View_All_Client]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - View_All_Client
*Author - Andrew Dockan
*Date - Oct 28, 2020 
*Date Last Modified - Dec 12, 2020
*Last Modified By - Kelly May
*Changes Made - refined output, added ClientAddressID for java use
*Description - Stored Procedure shows info for all clients from the clients table
*
*/

CREATE Procedure [dbo].[View_All_Client]

As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	Select c.ClientID, c.ClientName, luct.ClientType, c.ClientPhoneNumber, 
		ca.ClientAddressLine1, ca.ClientAddressLine2, ca.ClientAddressCity, 
		ca.ClientAddressState, ca.ClientAddressZip, ca.ClientAddressID
	From Clients  c
	Left Join ClientAddresses ca On c.ClientID = ca.ClientID
	Left Join LUClientType luct On c.ClientTypeID= luct.ClientTypeID
	;

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[View_All_Client_Shipments]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Matt Ridgway
-- Create date: 10-23-20
-- Description:	View Client Shipment info using clientID (variable @clientID) from dbo.Shipments
-- ===============================================
CREATE PROCEDURE [dbo].[View_All_Client_Shipments]

AS
BEGIN
DECLARE @clientID int,
		@TempClientID int,
		@Succ bit,
		@ErrorMsg NVarchar(50)

		SET @Succ =0;
		SET @ErrorMsg ='';
		SET @TempClientID=0;
		SET @clientID=0;
		BEGIN TRY
			BEGIN TRANSACTION;
				SELECT @TempClientID = ClientID
			FROM dbo.Shipments
				WHERE ClientID = (@ClientID)
				If @TempClientID = 0
			BEGIN
				SET @ErrorMsg= ' -7002 ClientID does not exist in Shipments'; 
				RAISERROR(@ErrorMsg,15,1);
				
			END	
			IF @@Error <> 0 
			BEGIN
				SELECT @ErrorMsg = Convert(nVarchar(50),@@ERROR) + '-7000';
				SET @Succ = 0;
				RAISERROR (@ErrorMsg,16,1);
			END

			SELECT @clientID FROM Shipments 
			WHERE @clientID = ClientID
			END TRY

		BEGIN CATCH
			ROLLBACK TRANSACTION;
			 IF @@ERROR <> 50000
					BEGIN
						SET @ErrorMsg = @ErrorMsg + CONVERT(nVarchar(50), @@Error);
					END
			   	
			 
			 SELECT @ErrorMsg =@ErrorMsg + ' -7001' +' Vew Failed';
			 SET @Succ =0;
			 
			 RAISERROR(@ErrorMsg, 16, 1);
			 	 
		
		END CATCH
END
GO
/****** Object:  StoredProcedure [dbo].[View_All_Client_Type]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - View_All_Client_Type
*Author - Andrew Dockan
*Date - Dec 10, 2020
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure shows info for all client types in the client type table
*
*/

Create Procedure [dbo].[View_All_Client_Type]
	
As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	Select *
	From LUClientType
	;

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[View_All_Flight]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - View_All_Flight
*Author - Andrew Dockan
*Date - Oct 28, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure shows info from all flights from flights table
*
*/

Create Procedure [dbo].[View_All_Flight]

As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	Select *
	From Flights
	Left Join Aircraft ac On Flights.AircraftID = ac.AircraftID
	Left Join Pilots p On Flights.PilotID = p.PilotID
	Left Join Airports ap1 On Flights.StartAirport = ap1.AirportID
	Left Join Airports ap2 On Flights.EndAirport = ap2.AirportID;

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[View_All_Pilot]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - View_All_Pilot
*Author - Kelly May
*Date - Oct 24, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure shows pilot information for all pilots
*
*/

CREATE PROCEDURE [dbo].[View_All_Pilot]

AS
BEGIN
BEGIN TRANSACTION

SELECT * FROM Pilots

COMMIT
END
GO
/****** Object:  StoredProcedure [dbo].[View_All_Shipments]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - View_All_Shipments
*Author - Matt Ridgway
*Date - Oct 23, 2020 
*Date Last Modified - Nov 12, 2020 
*Last Modified By - Andrew Dockan
*Changes Made - removed commit statement, not needed, causes runtime failure
*Description - View ALL Shipment info from dbo.Shipments
*
*/
CREATE PROCEDURE [dbo].[View_All_Shipments] 

AS
BEGIN
SELECT * FROM Shipments
END

GO
/****** Object:  StoredProcedure [dbo].[View_Selected_Aircraft]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - View_Selected_Aircraft
*Author - Kelly May
*Date - Oct 24, 2020 
*Date Last Modified - Dec 13, 2020
*Last Modified By - Andrew Dockan
*Changes Made - updated output for easier use with java program
*Description - Stored Procedure allows user to view Aircraft table, joined with the AircraftModels table, for 
* a given AircraftID
*
*/

CREATE PROCEDURE [dbo].[View_Selected_Aircraft]

@AircraftID int

AS
BEGIN

SELECT ac.AircraftID, ac.ACModelID, luacs.AircraftStatus, 
	acm.ACMake, acm.ACModel, acm.ACRange, 
	acm.ACRangeClassification, acm.ACPayload, acm.ACLoadVolume
FROM Aircraft ac
LEFT JOIN AircraftModels acm ON ac.ACModelID = acm.ACModelID
LEFT JOIN LUAircraftStatus luacs ON ac.AircraftStatusID = luacs.AircraftStatusID
WHERE ac.AircraftID = @AircraftID

END
GO
/****** Object:  StoredProcedure [dbo].[View_Selected_Aircraft_Model_By_Name]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - View_Selected_Aircraft_Model_By_Name
*Author - Andrew Dockan
*Date - Dec 13, 2020
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure shows info for specified aircraft modele in the aircarft model table
*
*/

CREATE Procedure [dbo].[View_Selected_Aircraft_Model_By_Name]
	(
	@ACModel varchar(20)
	)

As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	Select *
	From AircraftModels
	Where ACModel = @ACModel
	;

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[View_Selected_Client]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - View_Selected_Client
*Author - Andrew Dockan
*Date - Oct 28, 2020 
*Date Last Modified - Dec 10, 2020
*Last Modified By - Andrew Dockan
*Changes Made - removing extra fields from output, 
		changing to use values from other tables instead of ids 
*Description - Stored Procedure shows info for specified client in the clients table
*
*/

CREATE Procedure [dbo].[View_Selected_Client]
	(
	@ClientID int
	)

As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	Select cl.ClientID, cl.ClientName, luct.ClientType, cl.ClientPhoneNumber,
		ca.ClientAddressID,	ca.ClientAddressLine1, ca.ClientAddressLine2, 
		ca.ClientAddressCity, ca.ClientAddressState, ca.ClientAddressZip
	From Clients cl
	Left Join ClientAddresses ca On cl.ClientID = ca.ClientID
	Left Join LUClientType luct On cl.ClientTypeID= luct.ClientTypeID
	Where cl.ClientID = @ClientID
	;

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[View_Selected_Client_Type]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - View_Selected_Client
*Author - Andrew Dockan
*Date - Dec 10, 2020
*Date Last Modified - Dec 10, 2020
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure shows info for specified client in the clients table
*
*/

Create Procedure [dbo].[View_Selected_Client_Type]
	(
	@ClientTypeID int
	)

As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	Select *
	From LUClientType
	Where ClientTypeID = @ClientTypeID
	;

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[View_Selected_Client_Type_By_Name]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - View_Selected_Client_Type_By_Name
*Author - Andrew Dockan
*Date - Dec 11, 2020
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure shows info for specified client type in the client type table
*
*/

CREATE Procedure [dbo].[View_Selected_Client_Type_By_Name]
	(
	@ClientType varchar(30)
	)

As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	Select *
	From LUClientType
	Where ClientType = @ClientType
	;

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[View_Selected_Flight]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - View_Selected_Flight
*Author - Andrew Dockan
*Date - Oct 28, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure shows info from selected flight from flights table
*
*/

Create Procedure [dbo].[View_Selected_Flight]
	(
	@FlightID int
	)

As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	Select *
	From Flights	
	Left Join Aircraft ac On Flights.AircraftID = ac.AircraftID
	Left Join Pilots p On Flights.PilotID = p.PilotID
	Left Join Airports ap1 On Flights.StartAirport = ap1.AirportID
	Left Join Airports ap2 On Flights.EndAirport = ap2.AirportID
	Where FlightID = @FlightID
	;

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[View_Selected_Pilot_By_ID]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*Champlain Air Freight - View_Selected_Pilot_By_ID
*Author - Andrew Dockan
*Date - Nov 12, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure shows info for specified pilot in the pilots table
*
*/

Create Procedure [dbo].[View_Selected_Pilot_By_ID]
	(
	@PilotID int
	)

As
Begin
	
	Set NoCount On;

	Begin Transaction;
	
	Select *
	From Pilots
	Where Pilots.PilotID = @PilotID
	;

	Commit Transaction;

	Set NoCount Off;

End
;

GO
/****** Object:  StoredProcedure [dbo].[View_Selected_Pilot_By_Name]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*Champlain Air Freight - View_Selected_Pilot_By_Name
*Author - Kelly May
*Date - Oct 24, 2020 
*Date Last Modified - Nov 12, 2020
*Last Modified By - Andrew Dockan
*Changes Made - update procedure title to match functionality, 
*	and updated variables to accept proper length varchars
*Description - Stored Procedure shows pilot information based on the first and last name in the db
*
*/

CREATE PROCEDURE [dbo].[View_Selected_Pilot_By_Name]

@FirstName varchar(20), 
@LastName varchar(30)
AS
BEGIN
BEGIN TRANSACTION

SELECT * FROM Pilots WHERE 
FirstName = @FirstName AND
LastName = @LastName

COMMIT TRANSACTION
END
GO
/****** Object:  StoredProcedure [dbo].[View_Selected_Shipments]    Script Date: 12/16/2020 6:20:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*Champlain Air Freight - View_Selected_Shipments
*Author - Matt Ridgway
*Date - Oct 23, 2020 
*Date Last Modified - Nov 12, 2020
*Last Modified By - Andrew Dockan
*Changes Made - removed error handling, errored on valid data, added needed input field
*Description - View ShipmentID Shipment info using ShipID (variable @shipID) from dbo.Shipments
*/
CREATE PROCEDURE [dbo].[View_Selected_Shipments](
	@ShipmentID INT
)

AS
BEGIN

	BEGIN TRANSACTION;				

		SELECT * FROM Shipments 
		WHERE @ShipmentID = ShipmentID
			
	COMMIT TRANSACTION;
END
GO
USE [master]
GO
ALTER DATABASE [CAFDB] SET  READ_WRITE 
GO
