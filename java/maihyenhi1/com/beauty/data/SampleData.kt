package maihyenhi1.com.beauty.data

import maihyenhi1.com.beauty.R
import maihyenhi1.com.beauty.model.BeautySpot
import maihyenhi1.com.beauty.model.Review
import maihyenhi1.com.beauty.model.Service

object SampleData {

    val beautySpots = listOf(
        BeautySpot(
            id = 1,
            name = "RORA Spa & Massage Da Nang - 다낭 마사지 - 다낭 스파",
            address = "16-18 An Thượng 26, Bắc Mỹ Phú, Ngũ Hành Sơn, Đà Nẵng",
            imageUrl = "https://example.com/luala.jpg",
            imageResIds = listOf(
                R.drawable.rora,
                R.drawable.spa_1_3,
                R.drawable.spa_1_7,
                R.drawable.spa_1_5,
                R.drawable.spa_1_4,
                R.drawable.spa_1_6,
                R.drawable.spa_1_1,
                R.drawable.spa_1_2
            ),
            rating = 5.0f,
            description = "Rora Spa & Massage là thương hiệu massage thư giãn chất lượng hàng đầu nổi bật với chất lượng phục vụ đỉnh cao cùng với phong cách đồng quê, đậm nét văn hoá truyền thống Việt Nam.",
            services = listOf(
                Service("Rora Signature Massage'", 750, 90),
                Service("Leg Massage'", 540, 60),
                Service("Herbal Massage'", 540, 60),
                Service("Hot Stone Massage'", 450, 60)

            ),
            reviews = listOf(
                Review("Mai Anh", 5.0f, "Cơ sở mới mở, trang thiết bị vật chất mới mẻ, nhẹ nhàng. Phải cho Rora Spa vào danh sách những spa phải tới khi ở Đà Nẵng. Nhân viên nhiệt tình, kĩ thuật tốt, tư vấn kĩ càng,", "10/04/2025"),
                Review("딴따라", 5.0f, "너무 좋아서 2번왔어요\n" +
                        "첫날 마지막날 2번\n", "10/02/2025"),
                Review("Nhã Trương", 5.0f, "Spa Rora chất lượng phục vụ tốt, không gian sạch sẽ. Sau khi massage xông còn được thưởng thức trái cây rất ngon.", "10/04/2025"),
                Review("Hồng Nhung", 5.0f, "Nhân viên ở đây rất nhiệt tình và thân thiện, dịch vụ cũng khá tốt. Mới vào sẽ đưọc ngâm chân bằng thảo mộc và sau đó vào massage, xong đuợc ăn nhẹ sữa chua\n" +
                        "Nếu có quay lại Danang tôi sẽ ghé lại lần nữa", "05/03/2024")
            ),

            category = "Spa",
            mapUrl = "https://maps.google.com/maps?q=16-18+An+Thượng+26,+Mỹ+An,+Ngũ+Hành+Sơn,+Đà+Nẵng&t=&z=15&ie=UTF8&iwloc=&output=embed"
        ),
        BeautySpot(
            id = 2,
            name = "Hair salon Trí Hoàng Vũ",
            address = "59 Thái Phiên, Đà Nẵng",
            imageUrl = "https://example.com/glamour.jpg",
            imageResIds = listOf(
                R.drawable.salon_2_1,
                R.drawable.salon_2_2,
                R.drawable.salon_2_3,
                R.drawable.salon_2_4,
                R.drawable.salon_2_5,
                R.drawable.salon_2_7,
                R.drawable.salon_2_6
                ),
            rating = 4.8f,
            description = "Trí Hoàng Vũ Hair Salon là cái tên quen thuộc không chỉ của nhiều chị em mà còn là salon ruột của nhiều người nổi tiếng khi đến Đà Nẵng. Trí Hoàng Vũ Salon là đứa con tâm huyết của thương hiệu Khôi Hair Salon (Nguyễn Thế Khôi) – tạo mẫu chính 3 mùa Việt Nam NEXT TOP MODEL, chuyên gia tạo mẫu tóc cho người mẫu Lê Thúy, Tuyết Lan, nhạc sĩ Phương Uyên…",
            services = listOf(
                Service("Cắt + Gội + Sấy", 250, 60),
                Service("Nhuộm tóc trọn gói", 700, 120),
                Service("Uốn tóc cao cấp", 900, 150),
                Service("Phục hồi tóc Keratin", 1200, 120)
            ),
            reviews = listOf(
                Review("Hồng Nhung", 5.0f, "Stylist rất có tay nghề, tư vấn nhiệt tình và kiểu tóc phù hợp với khuôn mặt của mình. Không gian salon rất thoải mái và sang trọng.", "12/04/2025"),
                Review("Khả Ninh", 4.0f, "Mình đi nhiều tiệm ở Đà Nẵng rồi nhưng tiệm này mình thích nhất. Tóc uốn về rất mềm và bay như Hàn Quốc có điều do tóc mình thẳng nên hơi khó uốn nhưng vẫn ra nếp đẹp. 10₫ ko có nhưng ạ! \n.", "16/01/2025"),
                Review("Ewelina J Walasek", 5.0f, "I am very happy with my hair. They turned out just the way I wanted. Full professionalism, very friendly service. Highly recommend!\n.", "25/11/2024"),
                Review("Thanh Hằng", 4.5f, "Dịch vụ làm tóc tuyệt vời, nhân viên chuyên nghiệp. Tóc sau khi nhuộm giữ màu rất tốt.", "25/03/2025")
            ),
            category = "Hair",
            mapUrl = "https://maps.google.com/maps?q=59+Thái+Phiên,+Đà+Nẵng&t=&z=15&ie=UTF8&iwloc=&output=embed"
        ),
        BeautySpot(
            id = 3,
            name = "Nha khoa Ann Dentist",
            address = "208 Nguyễn Công Trứ, An Bắc Hải, quận Sơn Trà, TP. Đà Nẵng\n" ,
            imageUrl = "https://example.com/lotus.jpg",
            imageResIds = listOf(
                R.drawable.clinic_3_1,
                R.drawable.clinic_3_2,
                R.drawable.clinic_3_3,
                R.drawable.clinic_3_4,
                R.drawable.clinic_3_5,
                R.drawable.clinic_3_6,
                R.drawable.clinic_3_7
                ),
            rating = 4.9f,
            description = "Ann Dentist ra đời với mục tiêu mang đến cho tất cả khách hàng những dịch vụ và trải nghiệm tốt nhất về dịch vụ chăm sóc sức khỏe răng miệng. Đến với Ann Dentist quý khách hàng sẽ cảm nhận được sự tận tâm, chu đáo và sự thoải mái như đang ở chính ngôi nhà của mình.\n" +
                    "\n" +
                    "Với phương châm ” LẤY CHỮ TÂM” làm đầu!",
            services = listOf(
                Service("NIỀNG RĂNG MẮC CÀI", 6000, 30),
                Service("BỌC RĂNG SỨ THẨM MỸ", 8000, 45),
                Service("DÁN SỨ VENEER", 5000, 60),
                Service("ĐIỀU TRỊ TUỶ", 25000, 90)
            ),
            reviews = listOf(
                Review("Minh Tú", 5.0f, "Kết quả tiêm filler rất tự nhiên...", "02/04/2025"),
                Review("Kim Tuyến", 4.8f, "Dịch vụ chăm sóc khách hàng tuyệt vời...", "15/03/2025")
            ),
            category = "Clinic",
            mapUrl = "https://maps.google.com/maps?q=208+Nguyễn+Công+Trứ,+An+Bắc+Hải,+Sơn+Trà,+Đà+Nẵng&t=&z=15&ie=UTF8&iwloc=&output=embed"
        ),
        BeautySpot(
            id = 4,
            name = "Herbal Spa",
            address = "25 Trần Hưng Đạo, Quận 5, TP.HCM",
            imageUrl = "https://example.com/zen.jpg",
            imageResIds = listOf(
                R.drawable.massage_4_1,
                R.drawable.massage_4_2,
                R.drawable.massage_4_22,
                R.drawable.massage_4_3,
                R.drawable.massage_4_4,
                R.drawable.massage_4_5
            ),
            rating = 4.6f,
            description = "Zen Spa & Massage mang đến không gian thư giãn...",
            services = listOf(
                Service("Massage thư giãn toàn thân", 300, 60),
                Service("Massage chân", 450, 90),
                Service("Massage đá nóng", 600, 60),
                Service("Body Massage Herbal Spa Style", 1500, 150)
            ),
            reviews = listOf(
                Review("Lan Phương", 4.7f, "Lâu rồi mới quay lại Herbal, massage vẫn rất tuyệt vời. Mình và gia đình đã có một khoảng thời gian thư giãn. Cảm ơn Herbal, rất rcm mọi người nên ghé thăm một lần", "05/04/2025"),
                Review("Hà My", 4.5f, "Rât tôt. Rât đã. Xin lỗi vì đến muộn. Nhân viên rát chu dáo mặc dù tôi đến muộn. Herbal Spa là spa số một tại Đà Nẵng. Ngoài ra, tôi chắc chắn muốn đến đây khi đến thăm Đà Nẵng", "20/03/2025")
            ),
            category = "Spa",
            mapUrl = "https://maps.google.com/maps?q=25+Trần+Hưng+Đạo,+Quận+5,+TP.HCM&t=&z=15&ie=UTF8&iwloc=&output=embed"
        ),
        BeautySpot(
            id = 5,
            name = "Ruby Nails",
            address = "230 Đống Đa, Đà Nẵng",
            imageUrl = "https://example.com/nail.jpg",
            imageResIds = listOf(
                R.drawable.nail_5_5,
                R.drawable.nail_5_3,
                R.drawable.nail_5_1,
                R.drawable.nail_5_2,
                R.drawable.nail_5_4,
                R.drawable.nail_5_6
                ),
            rating = 4.3f,
            description = "Được biết đến là một địa chỉ làm nail uy tín và nổi tiếng tại Đà Nẵng, Ruby Nails chăm sóc từng ngón tay, ngón chân của chị em bằng cả trái tim...",
            services = listOf(
                Service("Làm móng tay cơ bản", 150, 45),
                Service("Sơn gel nghệ thuật", 250, 60),
                Service("Chăm sóc móng chân chuyên sâu", 200, 60)
            ),
            reviews = listOf(
                Review("Khánh Ly", 4.5f, "Nhân viên và cô chủ dễ thương, nhiệt tình. Đây là nơi mình rất rất ưng ý, và nhất định sẽ quay lại..", "25/05/2024"),
                Review("Diễm My", 4.0f, "Không gian thoải mái, giá hợp lý.", "18/03/2025")
            ),
            category = "Nail",
            mapUrl = "https://maps.google.com/maps?q=230+Đống+Đa,+Đà+Nẵng&t=&z=15&ie=UTF8&iwloc=&output=embed"
        ),
        BeautySpot(
            id = 6,
            name = "Hair Salon Beo Vĩnh Hoàng",
            address = "224 Lê Duẩn, Thanh Khê, TP. Đà Nẵng",
            imageUrl = "https://example.com/salon2.jpg",
            imageResIds = listOf(
                R.drawable.salon_6_1,
                R.drawable.salon_6_4,
                R.drawable.salon_6_5,
                R.drawable.salon_6_7,
                R.drawable.salon_6_6,
                R.drawable.salon_6_2,
                R.drawable.salon_6_3
                ),
            rating = 4.7f,
            description = "Là một salon lâu đời với quy mô và danh tiếng lớn, Salon Beo Vĩnh Hoàng luôn khiến mọi chị em phải gật gù hài lòng với dịch vụ tóc tại đây. Là cây kéo vàng đầu tiên tại Đà Nẵng, Beo Vĩnh Hoàng không chỉ hút khách với dịch vụ tạo kiểu chuyên nghiệp, mà còn đặc biệt hơn với dịch vụ phục hồi tóc hư tổn hàng đầu.",
            services = listOf(

                Service("Cắt + sấy tạo kiểu", 300, 60),
                Service("Nhuộm + Phục hồi tóc", 1100, 180),
                Service("Uốn tóc cao cấp", 900, 120),
                Service("Hấp dầu phục hồi", 400, 45)
            ),
            reviews = listOf(
                Review("Snow Flower", 4.8f, "Tiệm tóc chuyên nghiệp, nhân viên cắt tóc rất đẹp, dù là tóc cắt đơn giản nhưng khách trước và sau khi vào khác biệt rõ, dịch vụ chu đáo nhanh nhẹn 5 sao cho Beo\n.", "14/05/2024"),
                Review("Phương Thảo Vodatours", 4.6f, "Làm lần nào cũng vừa ý. Nhân viên chu đáo, tay nghề cao. Đặc biệt là a Bảo, chuyên nghiệp lắm .", "10/04/2023")
            ),
            category = "Hair",
            mapUrl = "https://maps.app.goo.gl/BgLQKrEZBazda5pL9"
        ),
        BeautySpot(
            id = 7,
            name = "30Shine Hair Salon",
            address = "12 Nguyễn Văn Linh, Hải Châu, Đà Nẵng",
            imageUrl = "https://example.com/30shine.jpg",
            imageResIds = listOf(
                R.drawable.salon_6_1, // Placeholder images
                R.drawable.salon_6_2
            ),
            rating = 4.9f,
            description = "30Shine là chuỗi salon tóc nam chuyên nghiệp với đội ngũ stylist hàng đầu, được đào tạo bài bản để mang đến những trải nghiệm tuyệt vời nhất cho khách hàng. Với không gian hiện đại, thiết bị tiên tiến và đa dạng dịch vụ chăm sóc tóc, 30Shine cam kết giúp bạn tỏa sáng với mái tóc đẹp tự nhiên và phong cách.",
            services = listOf(
                Service("Combo cắt gội 10 bước", 100, 45),
                Service("Uốn tóc Hàn Quốc", 350, 90),
                Service("Nhuộm màu thời trang", 280, 120),
                Service("Dưỡng tóc premium", 150, 30)
            ),
            reviews = listOf(
                Review("Minh Đức", 5.0f, "Dịch vụ chuyên nghiệp, nhân viên nhiệt tình, giá cả phải chăng. Haircut đẹp và phù hợp với khuôn mặt.", "15/04/2025"),
                Review("Hoàng Nam", 4.8f, "Cắt tóc xong lên hình rất đẹp, nhân viên tư vấn rất tận tâm.", "05/04/2025")
            ),
            category = "Hair",
            mapUrl = "https://maps.google.com/maps?q=12+Nguyễn+Văn+Linh,+Hải+Châu,+Đà+Nẵng&t=&z=15&ie=UTF8&iwloc=&output=embed"
        ),
        BeautySpot(
            id = 8,
            name = "Líp hair Studio",
            address = "10 Đặng Tất, Liên Chiểu, TP. Đà Nẵng",
            imageUrl = "https://example.com/tonydung.jpg",
            imageResIds = listOf(
                R.drawable.salon_8_0, // Placeholder images
                R.drawable.salon_8_1, // Placeholder images
                R.drawable.salon_8_2, // Placeholder images
                R.drawable.salon_8_3,
                R.drawable.salon_8_4,
                R.drawable.salon_8_5
                ),
            rating = 4.7f,
            description = "Líp hair Studio cũng là một địa chỉ chăm sóc tóc chuyên nghiệp tại Đà Nẵng. Đi lên từ chính lòng đam mê và sự tâm huyết với nghề, tính đến thời điểm này, Líp hair Studio đã có được một lượng khách quen đáng kể cũng như dần khẳng định được vị trí của mình tại thị trường tóc ở địa phương.\n" +
                    "\n" +
                    "Từ cắt, tạo kiểu tóc cho đến các dịch vụ uốn, nhuộm, phục hồi, tất cả đều được đảm bảo thực hiện theo đúng quy trình chuẩn xác. Nhiều mẫu tóc ra đời đã nhận được không ít các lời khen từ phía khách hàng bởi sự chăm chút một cách tỉ mỉ, từng đường kéo được thực hiện chính xác và trên hết đó là cái tâm của người thợ luôn được đặt vào mỗi tác phẩm của mình. Tóc uốn bồng bềnh, tóc duỗi siêu mềm mượt hay tóc ngắn cá tính với các kiểu như tóc bob, tóc ngang vai, ngắn uốn cụp,... tất cả đều không thể làm khó những tay kéo chuyên nghiệp tại đây. Chỉ cần bạn yêu cầu, Líp hair Studio sẽ thực hiện và luôn đảm bảo sẽ mang đến cho bạn một sự hài lòng trọn vẹn nhất.\n" +
                    "\n" +
                    "Không chỉ chinh phục khách hàng bởi chất lượng dịch vụ, Líp hair Studio còn tạo nên ấn tượng bởi một không gian chăm sóc tóc đảm bảo các yếu tố cả về thẩm mỹ lẫn thư giãn. Chính vì thế, chắc chắn một điều rằng khi bước vào đây, bạn sẽ thật sự hài lòng với tất cả mọi thứ thuộc về nơi này.",
            services = listOf(

                Service("Cắt + sấy tạo kiểu", 300, 60),
                Service("Uốn xoăn Hàn Quốc", 850, 120),
                Service("Nhuộm balayage", 1500, 180),
                Service("Hấp dầu phục hồi", 950, 90)
            ),
            reviews = listOf(
                Review("Thu Thảo", 4.7f, "Stylist tư vấn rất tận tình, kiểu tóc phù hợp với khuôn mặt và rất hợp thời trang.", "08/04/2025"),
                Review("Minh Hằng", 4.6f, "Làm tóc xong rất ưng ý, màu nhuộm lên chuẩn và bền đẹp.", "22/03/2025")
            ),
            category = "Hair",
            mapUrl = "https://maps.google.com/maps?q=46+Lê+Thanh+Nghị,+Hòa+Cường+Bắc,+Hải+Châu,+Đà+Nẵng&t=&z=15&ie=UTF8&iwloc=&output=embed"
        ),
        BeautySpot(
            id = 9,
            name = "Toc.House Hair Design",
            address = "122 Nguyễn Thị Minh Khai, Hải Châu, Đà Nẵng",
            imageUrl = "https://example.com/tochouse.jpg",
            imageResIds = listOf(
                R.drawable.salon_6_1, // Placeholder images
                R.drawable.salon_6_3
            ),
            rating = 4.8f,
            description = "Toc.House Hair Design là salon tóc cao cấp với phong cách thiết kế hiện đại, sang trọng. Đội ngũ stylist được đào tạo bài bản tại các học viện tóc nổi tiếng thế giới, luôn cập nhật những xu hướng tóc mới nhất và liên tục trau dồi kỹ năng để mang đến cho khách hàng những kiểu tóc đẹp nhất.",
            services = listOf(

                Service("Cắt tóc thời trang", 350, 45),
                Service("Uốn tóc cao cấp", 1200, 150),
                Service("Nhuộm highlight", 900, 120),
                Service("Combo trẻ hóa tóc", 1500, 180)
            ),
            reviews = listOf(
                Review("Thanh Trúc", 5.0f, "Salon sang trọng, thợ tay nghề cao, làm tóc xong rất đẹp và hợp với gương mặt.", "12/04/2025"),
                Review("Hải Yến", 4.7f, "Dịch vụ tuyệt vời, nhân viên chuyên nghiệp, tóc làm xong bồng bềnh tự nhiên.", "30/03/2025")
            ),
            category = "Hair",
            mapUrl = "https://maps.google.com/maps?q=122+Nguyễn+Thị+Minh+Khai,+Hải+Châu,+Đà+Nẵng&t=&z=15&ie=UTF8&iwloc=&output=embed"
        ),
        BeautySpot(
            id = 10,
            name = "HairSalon - Viet Paris",
            address = "203 Hoàng Diệu, Hải Châu, TP. Đà Nẵng",
            imageUrl = "https://example.com/ductri.jpg",
            imageResIds = listOf(
                R.drawable.salon_10_1, // Placeholder images
                R.drawable.salon_10_2, // Placeholder images
                R.drawable.salon_10_3,
                R.drawable.salon_10_4,
                R.drawable.salon_10_5,
                R.drawable.salon_10_6,
                R.drawable.salon_10_7
            ),
            rating = 4.6f,
            description = "Được mệnh danh là \"thương hiệu vàng\" trong lĩnh vực chăm sóc tóc tại Đà Nẵng, Việt Paris Hair Salon hiện là trung tâm làm tóc uy tín được sự ủng hộ nồng nhiệt từ chị em phụ nữ nơi đây cũng như một số nghệ sĩ nổi tiếng Vbiz.. Tiệm này có cũng lâu rồi, năm nào gần Tết các bạn trẻ cũng tới đây làm tóc, rất ưng ý và hài lòng, phục vụ rất chu đáo và chuyên nghiệp.\n" +
                    "\n" +
                    "\n" +
                    "Không gian và màu sắc của tiệm rất sang trọng. Phục vụ rất chu đáo và chuyên nghiệp. Nhân viên thì vui tính, nhiệt tình (rất thích điều này). Lúc nào cũng đông khách, khách nào cũng có cả nam lẫn nữ, trẻ em, một số người nước ngoài cũng tới làm đẹp, mấy ngày lễ Tết không có chỗ để ngồi làm.\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "Nếu bạn làm uốn với nhuộm thời gian hơi lâu tầm gần 7 tiếng, giá hơi cao nhưng bù lại chất lượng tốt đảm bảo các bạn làm quen rồi sẽ quay lại làm tiếp. Từ cắt, gội, nhuộm, uốn, massage da mặt …rất nhiều phản hồi hài lòng về chất lượng và giá cả. HairSalon - Viet Paris là địa chỉ rất đáng để tin cậy..",
            services = listOf(

                Service("Cắt + sấy tạo kiểu", 280, 60),
                Service("Uốn phồng Hàn Quốc", 750, 120),
                Service("Nhuộm balayage", 680, 120),
                Service("Hấp dầu phục hồi", 800, 90)
            ),
            reviews = listOf(
                Review("Hoàng Anh", 4.6f, "Cắt tóc đẹp, nhân viên tư vấn nhiệt tình, không gian salon sang trọng.", "18/04/2025"),
                Review("Minh Tuấn", 4.5f, "Uốn tóc ở đây rất đẹp và phù hợp, giữ nếp lâu.", "28/03/2025")
            ),
            category = "Hair",
            mapUrl = "https://maps.google.com/maps?q=68+Trần+Phú,+Hải+Châu,+Đà+Nẵng&t=&z=15&ie=UTF8&iwloc=&output=embed"
        ),
        BeautySpot(
            id = 11,
            name = "Hair & Spa Hưng Samurai",
            address = "23 Lê Độ, Thanh Khê, TP. Đà Nẵng",
            imageUrl = "https://example.com/mochairsalon.jpg",
            imageResIds = listOf(
                R.drawable.salon_11_1, // Placeholder images
                R.drawable.salon_11_2, // Placeholder images
                R.drawable.salon_11_6, // Placeholder images
                R.drawable.salon_11_5, // Placeholder images
                R.drawable.salon_11_4, // Placeholder images
                R.drawable.salon_11_3
            ),
            rating = 4.5f,
            description = "Nếu bạn muốn tận mắt chứng kiến kỹ thuật cắt tóc điêu luyện bằng 11 chiếc kéo cùng một lúc của nhà tạo mẫu tóc danh tiếng Nguyễn Hoàng Hưng - người được mệnh danh là \"Phù Thủy Tóc\" của xứ sở Đà Thành và cả Việt Nam thì nhất định không thể bỏ qua salon tóc Hưng Samurai.\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "Nhà tạo mẫu tóc Nguyễn Hoàng Hưng chủ salon Hưng Samurai là một trong số ít các nhà tạo mẫu tóc có tài năng thiên bẩm. Ngay từ nhỏ, cậu bé Hoàng Hưng đã rất yêu thích nghề tạo mẫu tóc và rất hay vẽ những mẫu tóc mới đầy sáng tạo. Chính mẹ anh – một giáo viên giỏi dạy nghề thẩm mỹ đã đạt giải “Bàn tay Vàng” phát hiện ra tài năng của anh ngay từ tấm bé. Và Hưng cũng đã cùng mẹ vượt qua bao khó khăn để đi lên từ hai bàn tay trắng.\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "Giờ đây trong giới showbiz không ai là không biết đến cây kéo nổi tiếng đất Đà Nẵng Nguyễn Hoàng Hưng. Rất nhiều nghệ sĩ là khách hàng thân quen của anh, nhưng không vì thế mà anh “kén khách”. Anh cắt tóc bằng cả trái tim nên không bao giờ có chuyện phân biệt khách sang, khách nghèo.\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "Đến với Hair Hưng Samurai, bạn sẽ được tư vấn kiểu tóc kỹ lưỡng bởi nhà tạo mẫu tóc chuyên nghiệp. Với niềm đam mê và tâm huyết với ngành tóc, anh chủ Hưng Samurai chắc chắn sẽ giúp khách hàng tìm thấy kiểu tóc đẹp & phù hợp nhất với công việc, sở thích và khuôn mặt của mỗi khách hàng. Điều đặc biệt ở salon này là anh Hưng - chủ Salon siêu có tâm sẽ tự tay thực hiện một cách tỉ mỉ cho mọi khách hàng của mình.\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "Hair Hưng Samurai được biết đến với kỹ thuật cắt tóc ngắn chuyên nghiệp, rất nhiều mái tóc ngắn của chị em Đà Nẵng đã được anh Hưng tự tay chăm chút cắt tỉa. Nếu muốn có một mái tóc đẹp và muốn được anh chủ có tâm này làm tóc cho mình thì đến ngay Salon nhé.",
            services = listOf(

                Service("Cắt + sấy tạo kiểu", 230, 45),
                Service("Uốn phồng Hàn Quốc", 800, 120),
                Service("Nhuộm balayage", 650, 120),
                Service("Phục hồi tóc thảo dược", 550, 60)
            ),
            reviews = listOf(
                Review("Lan Anh", 4.4f, "Rất thích không gian salon và các sản phẩm tự nhiên ở đây, tóc làm xong mềm mượt.", "20/04/2025"),
                Review("Thanh Tâm", 4.6f, "Nhân viên tư vấn chi tiết, làm tóc kỹ càng, kết quả rất hài lòng.", "01/04/2025")
            ),
            category = "Hair",
            mapUrl = "https://maps.google.com/maps?q=88+Hùng+Vương,+Hải+Châu,+Đà+Nẵng&t=&z=15&ie=UTF8&iwloc=&output=embed"
        )
    )
}