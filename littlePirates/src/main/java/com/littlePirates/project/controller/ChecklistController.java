package com.littlePirates.project.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.littlePirates.project.model.ChecklistVO;
import com.littlePirates.project.service.ChecklistService;

@Controller
public class ChecklistController {

	@Autowired
	ChecklistService service;

	@RequestMapping("/checkList/checkList_Manner")
	public String checkList_Manner(Model model) {
		ArrayList<ChecklistVO> voList = service.checklistInfo();
		model.addAttribute("voList", voList);
		return "/checkList/checkList_Manner";
	}

	// 빙고 체크리스트 최초생성
	@RequestMapping("/checkList/checkList_Clean")
	public String checkList_Clean(HttpSession session, Model model) {
		// memId에 저장
		// 로그인 성공 시 설정한 세션 sid 값 가져와서 사용
		String memId = (String) session.getAttribute("sid");

		ChecklistVO vo = new ChecklistVO();
		// (1) 체크리스트가 존재 하는지 확인 (생성 날짜가 없으면 없는 것)
		int count = service.ischhChecked(memId);

		if (count == 0) { // (2) 체크리스트 존재하지 않으면(count==0) 체크리스트 추가
			vo.setMemId(memId);
			ArrayList<String> rnd = service.checklistInfo2();
			for (int i = 0; i < rnd.size(); i++)
				vo.setChlNo(i, rnd.get(i));

			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String nowTime = sdf.format(now);
			System.out.println(nowTime);
			vo.setChhDate(nowTime);
			service.insertChecklist(vo);

			int countMemId = service.searchMemId(memId);

			service.insertTimes(memId, countMemId);

		} else {

			vo = service.selectChecklist(memId).get(0);
		}

		for (int i = 0; i < 9; i++) {
			String text = service.getChecklistText(vo.getChlNo(i));
			vo.setChlText(i, text);
		}

		model.addAttribute("vo", vo);
		return "/checkList/checkList_Clean";
	}

	/*
	 * @RequestMapping("/checkList/checkList_Clean") public String
	 * checkList_Checked(HttpSession session,Model model) { String memId
	 * =(String)session.getAttribute("sid"); ChecklistVO vo = new ChecklistVO();
	 * 
	 * return "/checkList/checkList_Clean"; }
	 */

	// 체크리스트 통계
	@RequestMapping("/checkList/checkList_Eat")
	public String checkList_Eat(Model model) {
		ArrayList<ChecklistVO> voList = service.checklistInfo3();
		model.addAttribute("voList", voList);

		return "/checkList/checkList_Eat";
	}

	@ResponseBody
	@RequestMapping("/checklist/checked")
	public String insert(@RequestParam("checkedNo") int checkedNo, HttpSession session) {

		String memId = (String) session.getAttribute("sid");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String nowTime = sdf.format(now);

		System.out.println(checkedNo);

		String checkDate = service.checkDate(memId, nowTime);
		String result="success";

		System.out.println(checkDate);
		if (checkDate == null) {

			String checkNo = service.checkNo(memId, checkedNo);

			System.out.println(checkNo);
			if (checkNo == null) {

				service.updateChecked(memId, checkedNo, nowTime);
			} else {
				System.out.println("넌 안돼~");
				result="fail";
			}
		} else {
			System.out.println("아 너 오늘 했잖아~");
			result="fail";
		}
		

		return result;
	}

}

/*
 * // 체크리스트 추가
 * 
 * @RequestMapping("/checkList/insertChecklist") public String
 * insertChecklist(HttpSession session,Model model) { // memId에 저장 // 로그인 성공 시
 * 설정한 세션 sid 값 가져와서 사용
 * 
 * String memId = (String)session.getAttribute("sid"); ChecklistVO vo = new
 * ChecklistVO();
 * 
 * 
 * // (1) 체크리스트가 존재 하는지 확인 (생성 날짜가 없으면 없는 것) int count =
 * service.ischhChecked(memId);
 * 
 * if(count == 0 ) { // (2) 체크리스트 존재하지 않으면(count==0) 체크리스트 추가
 * vo.setMemId(memId); ArrayList<String> rnd = service.checklistInfo2(); for(int
 * i=0; i<rnd.size();i++) vo.setChlNo(i,rnd.get(i)); Date now = new Date();
 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); String nowTime =
 * sdf.format(now); System.out.println(nowTime); vo.setChhDate(nowTime);
 * service.insertChecklist(vo); } else {
 * 
 * vo = service.selectChecklist(memId).get(0); } model.addAttribute("voList",
 * vo);
 * 
 * return "/checkList/checkList_Clean"; }
 */
