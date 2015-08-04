package com.mkyong;

import com.mkyong.DTO.ContractDTO;
import com.mkyong.DTO.TariffDTO;
import pdf.PdfUtil;

import java.io.*;
import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

@ManagedBean(name="user")
@SessionScoped
public class UserBean implements Serializable{

	private Client client = ClientBuilder.newClient();

	private Integer chosenTariffId;
	private String chosetTariffTitle;

	public Integer getChosenTariffId() {
		return chosenTariffId;
	}

	public void setChosenTariffId(Integer chosenTariffId) {
		this.chosenTariffId = chosenTariffId;
	}

	public Set<TariffDTO> getTariffs(){
		//todo make a constant
		Set<TariffDTO> tariffs = client.target("http://localhost:8080/eCareIS/rest/tariffs").
				request(MediaType.APPLICATION_JSON_TYPE).
				get(new GenericType<Set<TariffDTO>>() {});
		if (chosenTariffId==null) {
			for (TariffDTO tariffDTO : tariffs) {
				setChosenTariffId(tariffDTO.getTariffId());
				chosetTariffTitle = tariffDTO.getTitle();
				break;
			}
		}

		return tariffs;
	}

	public Set<ContractDTO> getTariffContracts(){
		//todo make a constant
		Set<ContractDTO> contractDTOs = client.target("http://localhost:8080/"
				+ "eCareIS/rest/contractsByTariff/tariff/"+getChosenTariffId()).
				request(MediaType.APPLICATION_JSON_TYPE).
				get(new GenericType<Set<ContractDTO>>() {
				});
		return contractDTOs;
	}

	public void tariffChangeValue(ValueChangeEvent e){
		System.out.println(e.getNewValue());
		setChosenTariffId((Integer) e.getNewValue());
	}

	public void printId(){
		System.out.println(getChosenTariffId());
	}

	public void downloadPDF() throws IOException {
		File pdfFile = PdfUtil.createPDF(getTariffContracts(), chosetTariffTitle );
		PdfUtil.uploadPDFToClient(pdfFile);
	}


}
